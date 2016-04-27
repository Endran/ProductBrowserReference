/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import mockit.Injectable
import mockit.Verifications
import nl.endran.productbrowser.interactors.Product
import org.junit.Test

class DetailFragmentPresenterTest {

    @Test
    fun ShouldShowProductOnViewModel_WhenStarted(@Injectable viewModel : DetailFragmentPresenter.ViewModel, @Injectable product: Product) {
        val presenter = DetailFragmentPresenter(product)
        presenter.start(viewModel)

        object : Verifications(){
            init {
                viewModel.showProduct(product)
            }
        }
    }
}