/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import android.view.View
import kotlinx.android.synthetic.main.fragment_skeleton.view.*
import nl.endran.productbrowser.R
import nl.endran.productbrowser.injections.showSnackBar
import nl.endran.productbrowser.injections.showToast
import nl.endran.productbrowser.mvp.BaseFragmentView
import javax.inject.Inject

class SkeletonFragmentView @Inject constructor() : BaseFragmentView<SkeletonFragmentPresenter.ViewModel, SkeletonFragmentPresenter>() {

    override fun getViewId() = R.layout.fragment_skeleton

    override fun prepare(rootView: View) {
        rootView.buttonToast.setOnClickListener {
            presenter?.buttonToastClicked(rootView.editTextMessage.text.toString())
        }

        rootView.buttonSnackbar.setOnClickListener {
            presenter?.buttonSnackbarClicked(rootView.editTextMessage.text.toString())
        }
    }

    override fun getViewModel() = object : SkeletonFragmentPresenter.ViewModel {

        override fun showToast(message: String) {
            rootView?.showToast(message)
        }

        override fun showSnackbar(message: String) {
            rootView?.showSnackBar(message)
        }
    }
}
