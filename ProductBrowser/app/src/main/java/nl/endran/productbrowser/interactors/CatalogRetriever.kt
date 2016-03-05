/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.interactors

import com.google.gson.Gson
import nl.endran.productbrowser.datatypes.Catalog
import nl.endran.productbrowser.datatypes.Product
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.toSingletonObservable
import rx.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CatalogRetriever @Inject constructor() {

    val observable = this.toSingletonObservable()
            .subscribeOn(Schedulers.io())
            .repeatWhen { Observable.interval(5, TimeUnit.MINUTES) }
            .map { getProductArrayJson() }
            .map { Gson().fromJson(it, Array<Product>::class.java) }
            .map { Catalog(it.toList()) }
            .observeOn(AndroidSchedulers.mainThread())


    private fun getProductArrayJson(): String {
        val url = URL("https://gist.githubusercontent.com/anonymous/a3b3e50413fff111505a/raw/0522419f508e7ea506a8856586dce11a5664e9df/products.json");
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()));

        var line: String?
        val stringBuilder = StringBuilder();

        line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
        }

        bufferedReader.close();

        return stringBuilder.toString();
    }
}