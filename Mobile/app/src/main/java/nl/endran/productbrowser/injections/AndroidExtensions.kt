/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.injections

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import nl.endran.productbrowser.App

fun Context.getAppComponent(): AppComponent {
    return (applicationContext as App).appComponent
}

fun View.getLayoutInflater(): LayoutInflater {
    return LayoutInflater.from(context)
}

fun View.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}
