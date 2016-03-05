/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import nl.endran.productbrowser.injections.AppComponent
import nl.endran.productbrowser.mvp.BaseFragment

class OverviewFragment : BaseFragment<OverviewFragmentPresenter.ViewModel, OverviewFragmentPresenter, OverviewFragmentView>() {

    companion object {
        fun createInstance() = OverviewFragment()
    }

    override fun createView(appComponent: AppComponent): OverviewFragmentView {
        return appComponent.overviewFragmentView
    }

    override fun createPresenter(appComponent: AppComponent): OverviewFragmentPresenter {
        return appComponent.overviewFragmentPresenter
    }
}