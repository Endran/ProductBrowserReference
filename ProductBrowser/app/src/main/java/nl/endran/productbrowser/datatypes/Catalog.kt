/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.datatypes

import org.joda.time.DateTime

data class Product(val name: String, val category: String, val items_remaining: Int, val image_url: String, val description: String)
data class Catalog(val retrievedAt: DateTime, val products: List<Product>)
