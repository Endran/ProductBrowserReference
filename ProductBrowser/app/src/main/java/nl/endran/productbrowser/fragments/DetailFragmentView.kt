/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import android.text.Html
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.view.*
import nl.endran.productbrowser.R
import nl.endran.productbrowser.interactors.Product
import nl.endran.productbrowser.mvp.BaseFragmentView
import javax.inject.Inject

class DetailFragmentView @Inject constructor() : BaseFragmentView<DetailFragmentPresenter.ViewModel, DetailFragmentPresenter>() {

    override fun getViewId() = R.layout.fragment_detail

    override fun prepare(rootView: View) {
    }

    override fun getViewModel() = object : DetailFragmentPresenter.ViewModel {
        override fun showProduct(product: Product) {
            if (rootView != null) {
                rootView!!.textViewName.text = product.name
                rootView!!.textViewCategory.text = product.category
                rootView!!.textViewRemaining.text = "${product.items_remaining}"
                rootView!!.textViewDescription.text = Html.fromHtml(product.description)

                Glide.with(rootView!!.context).load(product.image_url)
                        .placeholder(R.color.colorPrimaryLight)
                        .centerCrop()
                        .into(rootView!!.imageView);
            }
        }

    }
}
