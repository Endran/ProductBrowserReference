/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.example

class ModelInteractor(val name: String) {
    fun executeSomeComplexOperation(message: String, function: (String) -> Unit) {
        function("ModelInteractor $name received ${prepareMessage(message)}")
    }

    private fun prepareMessage(message: String): String {
        if (message.isEmpty()) {
            return "an empty message"
        } else {
            return message
        }
    }
}
