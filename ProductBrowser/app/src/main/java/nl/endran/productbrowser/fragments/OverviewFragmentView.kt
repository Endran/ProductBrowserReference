/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.fragments

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_overview.view.*
import kotlinx.android.synthetic.main.itemview_product.view.*
import nl.endran.productbrowser.R
import nl.endran.productbrowser.injections.getLayoutInflater
import nl.endran.productbrowser.interactors.Catalog
import nl.endran.productbrowser.interactors.Product
import nl.endran.productbrowser.mvp.BaseFragmentView
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class OverviewFragmentView @Inject constructor() : BaseFragmentView<OverviewFragmentPresenter.ViewModel, OverviewFragmentPresenter>() {

    private val productAdapter = ProductAdapter();

    override fun getViewId(): Int = R.layout.fragment_overview

    override fun getViewModel(): OverviewFragmentPresenter.ViewModel = object : OverviewFragmentPresenter.ViewModel {
        override fun showProducts(catalog: Catalog) {
            rootView?.swipeRefreshLayout?.isRefreshing = false
            rootView?.textViewNumberOfItems?.text = "${catalog.products.size}"
            rootView?.textViewLatestRetrieval?.text = "${DateTimeFormat.mediumDateTime().print(catalog.retrievedAt)}"
            productAdapter.products = catalog.products
            productAdapter.notifyDataSetChanged()
        }
    }

    override fun prepare(rootView: View) {
        rootView.textViewNumberOfItems.text = ""
        rootView.textViewLatestRetrieval.text = ""
        rootView.recyclerView.adapter = productAdapter
        rootView.swipeRefreshLayout.setOnRefreshListener { presenter?.refresh() }
    }

    private inner class ProductAdapter : RecyclerView.Adapter<ViewHolder>() {

        var products: List<Product> = listOf()

        override fun getItemCount() = products.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
            val itemView = parent!!.getLayoutInflater().inflate(R.layout.itemview_product, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
            val itemView = viewHolder!!.itemView
            val product = products[position]

            itemView.textViewTitle.text = product.name
            itemView.textViewStock.text = "${product.items_remaining}"
            Glide.with(itemView.context).load(product.image_url)
                    .placeholder(R.color.colorPrimaryLight)
                    .centerCrop()
                    .into(itemView.imageView);
            itemView.setOnClickListener { presenter?.productSelected(product) }
        }
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
