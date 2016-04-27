/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.interactors

import android.content.res.Resources
import com.f2prateek.rx.preferences.Preference
import com.f2prateek.rx.preferences.RxSharedPreferences
import com.google.gson.Gson
import nl.endran.productbrowser.R
import org.joda.time.DateTime
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.toSingletonObservable
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class Product(val name: String, val category: String, val items_remaining: Int, val image_url: String, val description: String)
data class Catalog(val products: List<Product>, val retrievedAt: DateTime = DateTime.now())

class CatalogRetriever @Inject constructor(resources: Resources, rxSharedPreferences: RxSharedPreferences, val urlReader: UrlReader) {

    val serverUrl: String

    private val catalogJsonPreferences: Preference<String>
    private val datePreferences: Preference<String>
    private var repeatSubscription: Subscription? = null

    init {
        serverUrl = resources.getString(R.string.serverUrl)
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
        refresh()
    }

    fun stop() {
        repeatSubscription?.unsubscribe()
        repeatSubscription = null
    }

    fun refresh() {
        toSingletonObservable()
                .subscribeOn(Schedulers.io())
                .map { urlReader.read(serverUrl) }
                .filter { it.isNotEmpty() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { datePreferences.set(DateTime.now().toString()) }
                .subscribe(catalogJsonPreferences.asAction())
    }
}
