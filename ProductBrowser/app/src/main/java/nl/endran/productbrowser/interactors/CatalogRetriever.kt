/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.interactors

import com.f2prateek.rx.preferences.Preference
import com.f2prateek.rx.preferences.RxSharedPreferences
import com.google.gson.Gson
import org.joda.time.DateTime
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.toSingletonObservable
import rx.schedulers.Schedulers
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class Product(val name: String, val category: String, val items_remaining: Int, val image_url: String, val description: String)
data class Catalog(val products: List<Product>, val retrievedAt: DateTime = DateTime.now())

class CatalogRetriever @Inject constructor(rxSharedPreferences: RxSharedPreferences) {

    private val catalogJsonPreferences: Preference<String>
    private val datePreferences: Preference<String>
    private var repeatSubscription: Subscription? = null

    init {
        catalogJsonPreferences = rxSharedPreferences.getString("CATALOG_KEY", "");
        datePreferences = rxSharedPreferences.getString("DATE_KEY", "");
    }

    val observable = datePreferences.asObservable()
            .subscribeOn(Schedulers.computation())
            .filter { datePreferences.get()?.isNotEmpty() }
            .map { Gson().fromJson(catalogJsonPreferences.get(), Array<Product>::class.java) }
            .filter { it != null }
            .map { Catalog(it.toList(), DateTime(datePreferences.get())) }
            .observeOn(AndroidSchedulers.mainThread())

    fun start() {
        repeatSubscription = toSingletonObservable()
                .subscribeOn(Schedulers.computation())
                .repeatWhen { Observable.interval(5, TimeUnit.MINUTES) }
                .subscribe() { refresh() }
    }

    fun stop() {
        repeatSubscription?.unsubscribe()
        repeatSubscription = null
    }

    fun refresh() {
        toSingletonObservable()
                .subscribeOn(Schedulers.io())
                .map { getProductArrayJson() }
                .filter { it.isNotEmpty() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { datePreferences.set(DateTime.now().toString()) }
                .subscribe(catalogJsonPreferences.asAction())
    }

    private fun getProductArrayJson(): String {
        val stringBuilder = StringBuilder();
        try {
            val url = URL("https://gist.githubusercontent.com/anonymous/a3b3e50413fff111505a/raw/0522419f508e7ea506a8856586dce11a5664e9df/products.json");
            val bufferedReader = BufferedReader(InputStreamReader(url.openStream()));

            var line: String?

            line = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = bufferedReader.readLine()
            }

            bufferedReader.close();
        } catch (e: Exception) {
            Timber.e("Could not read from url", e)
        }

        return stringBuilder.toString();
    }
}
