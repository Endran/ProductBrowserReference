/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import android.view.View
import kotlinx.android.synthetic.main.fragment_overview.view.*
import nl.endran.productbrowser.R
import nl.endran.productbrowser.datatypes.Catalog
import nl.endran.productbrowser.mvp.BaseFragmentView
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class OverviewFragmentView @Inject constructor() : BaseFragmentView<OverviewFragmentPresenter.ViewModel, OverviewFragmentPresenter>() {

    override fun getViewId(): Int = R.layout.fragment_overview

    override fun getViewModel(): OverviewFragmentPresenter.ViewModel = object : OverviewFragmentPresenter.ViewModel {
        override fun showProducts(catalog: Catalog) {
            rootView?.textViewNumberOfItems?.text = "${catalog.products.size}"
            rootView?.textViewLatestRetrieval?.text = "${DateTimeFormat.shortDateTime().print(catalog.retrievedAt)}"
        }
    }

    override fun prepare(rootView: View) {
        rootView.textViewNumberOfItems.text = ""
        rootView.textViewLatestRetrieval.text = ""
    }

}