/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import nl.endran.productbrowser.injections.AppComponent
import nl.endran.productbrowser.mvp.BaseFragment

class SkeletonFragment : BaseFragment<SkeletonFragmentPresenter.ViewModel, SkeletonFragmentPresenter, SkeletonFragmentView>() {

    companion object {
        fun createInstance() = SkeletonFragment()
    }

    override fun createView(appComponent: AppComponent): SkeletonFragmentView {
        return appComponent.skeletonFragmentView
    }

    override fun createPresenter(appComponent: AppComponent): SkeletonFragmentPresenter {
        return appComponent.skeletonFragmentPresenter
    }
}
