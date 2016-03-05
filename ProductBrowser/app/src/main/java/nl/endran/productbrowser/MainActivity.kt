/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nl.endran.productbrowser.fragments.OverviewFragment
import nl.endran.productbrowser.injections.getAppComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        getAppComponent().inject(this)

        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.contentView, OverviewFragment.createInstance())
        transition.commit()
    }
}
