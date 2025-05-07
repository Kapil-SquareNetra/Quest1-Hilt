package com.devgun.quest1_hilt.data.remote.api.products.model


data class Product(
    val id: Int,
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val category: String = "",
    val thumbnail: String = ""
)
