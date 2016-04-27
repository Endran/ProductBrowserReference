/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.interactors

import android.content.Context
import android.content.Intent
import mockit.*
import nl.endran.productbrowser.AboutActivity
import nl.endran.productbrowser.DetailActivity
import org.junit.Test

class ScreenFlowControllerTest {

    @Injectable
    lateinit var context: Context


    @Tested
    lateinit var screenFlowController: ScreenFlowController

    @Test
    fun ShouldStartDetailActivityIntent_WhenAsked(@Mocked detailActivity: DetailActivity.Companion, @Injectable product: Product, @Injectable intent: Intent) {
        object : Expectations() {
            init {
                DetailActivity.createIntent(context, product)
                result = intent
            }
        }

        screenFlowController.showProductDetail(product)

        object : Verifications() {
            init {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    @Test
    fun ShouldStartAboutActivityIntent_WhenAsked(@Mocked detailActivity: AboutActivity.Companion, @Injectable product: Product, @Injectable intent: Intent) {
        object : Expectations() {
            init {
                AboutActivity.createIntent(context)
                result = intent
            }
        }

        screenFlowController.showAbout()

        object : Verifications() {
            init {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }
}