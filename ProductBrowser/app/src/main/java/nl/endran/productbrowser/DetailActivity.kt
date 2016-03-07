/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import nl.endran.productbrowser.datatypes.Product
import nl.endran.productbrowser.fragments.DetailFragment

class DetailActivity : AppCompatActivity() {

    companion object {
        val PRODUCT_KEY = "PRODUCT_KEY"

        fun createIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PRODUCT_KEY, Gson().toJson(product))
            return intent
        }

        fun getProduct(intent: Intent) = Gson().fromJson(intent.getStringExtra(PRODUCT_KEY), Product::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val product = getProduct(intent)

        supportActionBar?.title = product.name

        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.contentView, DetailFragment.createInstance(product))
        transition.commit()
    }
}
