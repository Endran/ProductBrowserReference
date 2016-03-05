/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.kotlin.mvp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mockit.Expectations
import mockit.Mocked
import mockit.Verifications
import nl.endran.productbrowser.App
import nl.endran.productbrowser.injections.AppComponent
import nl.endran.productbrowser.mvp.BaseFragment
import nl.endran.productbrowser.mvp.BaseFragmentPresenter
import nl.endran.productbrowser.mvp.BaseFragmentView
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class BaseFragmentTest {

    @Mocked
    lateinit var layoutInflaterMock: LayoutInflater

    @Mocked
    lateinit var appMock: App

    @Mocked
    lateinit var appComponentMock: AppComponent

    @Mocked
    lateinit var viewGroupMock: ViewGroup

    @Mocked
    lateinit var androidViewMock: View

    @Mocked
    lateinit var viewMock: BaseFragmentView<Any, BaseFragmentPresenter<Any>>

    @Mocked
    lateinit var presenterMock: BaseFragmentPresenter<Any>

    lateinit var baseFragment: TestBaseFragment

    @Before
    fun setUp() {
        prepareViewCreationExpectations()

        baseFragment = TestBaseFragment()
    }

    @Test
    fun shouldReturnAndroidViewCreatedByView_WhenOnCreateViewIsCalled() {
        object : Expectations() {
            init {
                viewMock.inflate(layoutInflaterMock, viewGroupMock, null)
                result = androidViewMock
            }
        }

        val androidView = baseFragment.onCreateView(layoutInflaterMock, viewGroupMock, null)

        Assertions.assertThat(androidView).isSameAs(androidViewMock)
    }

    @Test
    fun shouldCallAndroidViewReadyOnView_whenOnViewCreatedIsCalled() {
        baseFragment.onCreateView(layoutInflaterMock, viewGroupMock, null)

        baseFragment.onViewCreated(null, null)

        object : Verifications() {
            init {
                viewMock.androidViewReady()
            }
        }
    }

    @Test
    fun shouldCallDeflateOnView_whenOnViewIsDestroyed() {
        baseFragment.onCreateView(layoutInflaterMock, viewGroupMock, null)

        baseFragment.onDestroyView()

        object : Verifications() {
            init {
                viewMock.deflate()
            }
        }
    }

    @Test
    fun shouldCreateAndSetPresenterOnViewViaStart_whenOnResumeIsCalled() {
        baseFragment.onCreateView(layoutInflaterMock, viewGroupMock, null)

        preparePresenterCreationExpectations()
        baseFragment.onResume()

        object : Verifications() {
            init {
                viewMock.start(presenterMock)
            }
        }
    }

    @Test
    fun shouldStopView_whenOnPauseIsCalled() {
        baseFragment.onCreateView(layoutInflaterMock, viewGroupMock, null)

        baseFragment.onPause()

        object : Verifications() {
            init {
                viewMock.stop()
            }
        }
    }

    @Test
    fun shouldBeNullSafe_whenLifecycleCallbacksAreCalledAfterOnDestroy() {
        baseFragment.onCreateView(layoutInflaterMock, viewGroupMock, null)
        baseFragment.onDestroyView()

        baseFragment.onViewCreated(null, null)
        baseFragment.onPause()
        baseFragment.onResume()
        baseFragment.onDestroyView()
    }

    // ---------------


     inner class TestBaseFragment : BaseFragment<Any, BaseFragmentPresenter<Any>, BaseFragmentView<Any, BaseFragmentPresenter<Any>>>() {
        override fun createView(appComponent: AppComponent): BaseFragmentView<Any, BaseFragmentPresenter<Any>> {
            Assertions.assertThat(appComponent).isSameAs(appComponentMock)
            return viewMock
        }

        override fun createPresenter(appComponent: AppComponent): BaseFragmentPresenter<Any> {
            Assertions.assertThat(appComponent).isSameAs(appComponentMock)
            return presenterMock
        }

        override fun getContext(): Context? {
            return appMock
        }
    }

    private fun prepareViewCreationExpectations() {
        object : Expectations() {
            init {
                layoutInflaterMock.context
                result = appMock
            }

            init {
                appMock.applicationContext
                result = appMock
            }

            init {
                appMock.appComponent
                result = appComponentMock
            }
        }
    }

    private fun preparePresenterCreationExpectations() {
        object : Expectations() {

            init {
                appMock.applicationContext
                result = appMock
            }

            init {
                appMock.appComponent
                result = appComponentMock
            }
        }
    }
}
