/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.kotlin.example

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class ModelInteractorKotlinJunitTest {

    val testName = "TEST_NAME"

    lateinit var interactor: ModelInteractor

    @Before
    fun setUp() {

        interactor = ModelInteractor(testName)
    }

    @Test
    fun ShouldInformViaCallbackThatTheMessageIsEmpty_WhenEmptyMessageIsProvided() {
        var actual = ""
        interactor.executeSomeComplexOperation("") {
            actual = it
        }

        val expected = "ModelInteractor $testName received an empty message"
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun ShouldInformViaCallbackAMessage_WhenNonEmptyMessageIsProvided() {
        val testMessage = "testMessage"

        var actual = ""
        interactor.executeSomeComplexOperation(testMessage) {
            actual = it
        }

        val expected = "ModelInteractor $testName received $testMessage"
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
