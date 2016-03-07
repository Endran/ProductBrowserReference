/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.interactors

import android.content.Context
import android.content.Intent
import nl.endran.productbrowser.AboutActivity
import nl.endran.productbrowser.DetailActivity
import javax.inject.Inject

class ScreenFlowController @Inject constructor(val context: Context) {

    fun showProductDetail(product: Product) {
        val intent = DetailActivity.createIntent(context, product)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun showAbout() {
        val intent = AboutActivity.createIntent(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}
