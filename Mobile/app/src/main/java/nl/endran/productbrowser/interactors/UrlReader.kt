/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.interactors

import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject

class UrlReader @Inject constructor() {
    fun read(url: String): String {
        val stringBuilder = StringBuilder();
        try {
            val bufferedReader = BufferedReader(InputStreamReader(URL(url).openStream()));

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
