/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import nl.endran.productbrowser.interactors.Catalog
import nl.endran.productbrowser.interactors.CatalogRetriever
import nl.endran.productbrowser.interactors.Product
import nl.endran.productbrowser.interactors.ScreenFlowController
import nl.endran.productbrowser.mvp.BaseFragmentPresenter
import rx.Subscription
import javax.inject.Inject

class OverviewFragmentPresenter @Inject constructor(
        val screenFlowController: ScreenFlowController,
        val catalogRetriever: CatalogRetriever) : BaseFragmentPresenter<OverviewFragmentPresenter.ViewModel>() {

    interface ViewModel {
        fun showProducts(catalog: Catalog)
    }

    var subscription: Subscription? = null

    override fun onStart() {
        catalogRetriever.start()
        subscription = catalogRetriever.observable.subscribe() {
            viewModel?.showProducts(it)
        }
    }

    override fun onStop() {
        subscription?.unsubscribe()
        subscription = null
        catalogRetriever.stop()
    }

    fun productSelected(product: Product) {
        screenFlowController.showProductDetail(product)
    }

    fun refresh() {
        catalogRetriever.refresh()
    }
}
