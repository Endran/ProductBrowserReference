/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import mockit.Expectations
import mockit.Injectable
import mockit.Tested
import mockit.Verifications
import nl.endran.productbrowser.interactors.Catalog
import nl.endran.productbrowser.interactors.CatalogRetriever
import nl.endran.productbrowser.interactors.Product
import nl.endran.productbrowser.interactors.ScreenFlowController
import org.junit.Test
import rx.Observable
import rx.Subscription
import rx.functions.Action1
import rx.lang.kotlin.BehaviourSubject

class OverviewFragmentPresenterTest {

    @Injectable
    lateinit var screenFlowController: ScreenFlowController

    @Injectable
    lateinit var catalogRetriever: CatalogRetriever

    @Injectable
    lateinit var viewModel: OverviewFragmentPresenter.ViewModel

    @Tested
    lateinit var presenter: OverviewFragmentPresenter

    @Test
    fun ShouldStartCatalogRetriever_WhenStarted() {
        presenter.start(viewModel)

        object : Verifications() {
            init {
                catalogRetriever.start()
            }
        }
    }

    @Test
    fun ShouldStopCatalogRetriever_WhenStopped() {
        presenter.stop()

        object : Verifications() {
            init {
                catalogRetriever.stop()
            }
        }
    }

    @Test
    fun ShouldShowCatalog_WhenSubscribedObserverEmits(@Injectable catalog: Catalog) {
        val subject = BehaviourSubject<Catalog>()

        object : Expectations() {
            init {
                catalogRetriever.observable
                result = subject
            }
        }

        presenter.start(viewModel)
        subject.onNext(catalog)

        object : Verifications() {
            init {
                viewModel.showProducts(catalog)
            }
        }
    }

    @Test
    fun ShouldUnSubscribe_WhenStopped(@Injectable catalogObservable: Observable<String>, @Injectable subscription: Subscription, @Injectable action: Action1<String>) {
        object : Expectations() {
            init {
                catalogRetriever.observable
                result = catalogObservable
                catalogObservable.subscribe(withAny(action))
                result = subscription
            }
        }

        presenter.start(viewModel)
        presenter.stop()

        object : Verifications() {
            init {
                subscription.unsubscribe()
            }
        }
    }

    @Test
    fun ShouldForwardProductToScreenFlowController_WhenSelected(@Injectable product: Product) {
        presenter.productSelected(product)

        object : Verifications() {
            init {
                screenFlowController.showProductDetail(product)
            }
        }
    }

    @Test
    fun ShouldRefreshCatalog_WhenRefreshed() {
        presenter.refresh()

        object : Verifications() {
            init {
                catalogRetriever.refresh()
            }
        }
    }
}
