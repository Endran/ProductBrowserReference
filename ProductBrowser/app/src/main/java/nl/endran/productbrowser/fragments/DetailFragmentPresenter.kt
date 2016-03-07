/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import nl.endran.productbrowser.datatypes.Product
import nl.endran.productbrowser.mvp.BaseFragmentPresenter
import javax.inject.Inject

class DetailFragmentPresenter constructor(val product: Product)
: BaseFragmentPresenter<DetailFragmentPresenter.ViewModel>() {

    interface ViewModel {
        fun showProduct(product: Product)
    }

    override fun onStart() {
        viewModel?.showProduct(product)
    }

    override fun onStop() {
    }

    class Factory @Inject constructor() {
        fun create(product: Product) = DetailFragmentPresenter(product)
    }
}
