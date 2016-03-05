/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import nl.endran.productbrowser.datatypes.Catalog
import nl.endran.productbrowser.mvp.BaseFragmentPresenter
import org.joda.time.DateTime
import javax.inject.Inject

class OverviewFragmentPresenter @Inject constructor()
: BaseFragmentPresenter<OverviewFragmentPresenter.ViewModel>() {

    interface ViewModel {
        fun showProducts(catalog: Catalog)
    }

    override fun onStart() {
        viewModel?.showProducts(Catalog(DateTime.now(), listOf()))
    }

    override fun onStop() {
        // Stop any running operation that might be busy in the background
    }
}