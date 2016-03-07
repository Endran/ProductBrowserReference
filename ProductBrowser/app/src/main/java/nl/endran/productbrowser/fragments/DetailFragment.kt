/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import android.os.Bundle
import com.google.gson.Gson
import nl.endran.productbrowser.datatypes.Product
import nl.endran.productbrowser.injections.AppComponent
import nl.endran.productbrowser.mvp.BaseFragment

class DetailFragment : BaseFragment<DetailFragmentPresenter.ViewModel, DetailFragmentPresenter, DetailFragmentView>() {

    companion object {
        val PRODUCT_KEY = "PRODUCT_KEY"

        fun createInstance(product: Product): DetailFragment {
            val arguments = Bundle()
            arguments.putString(PRODUCT_KEY, Gson().toJson(product))

            val fragment = DetailFragment()
            fragment.arguments = arguments
            return fragment
        }

        fun getProduct(arguments: Bundle) = Gson().fromJson(arguments.getString(PRODUCT_KEY), Product::class.java)
    }

    override fun createView(appComponent: AppComponent): DetailFragmentView {
        return appComponent.detailFragmentView
    }

    override fun createPresenter(appComponent: AppComponent): DetailFragmentPresenter {
        val detailFragmentPresenterFactory = appComponent.detailFragmentPresenterFactory
        return detailFragmentPresenterFactory.create(getProduct(arguments))
    }
}
