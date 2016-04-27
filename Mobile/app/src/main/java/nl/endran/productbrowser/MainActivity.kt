/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import nl.endran.productbrowser.fragments.OverviewFragment
import nl.endran.productbrowser.injections.getAppComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.contentView, OverviewFragment.createInstance())
        transition.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about ) {
            getAppComponent().screenFlowController.showAbout()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}
