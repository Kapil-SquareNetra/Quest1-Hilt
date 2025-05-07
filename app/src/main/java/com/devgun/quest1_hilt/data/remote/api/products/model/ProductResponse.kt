package com.devgun.quest1_hilt.data.remote.api.products.model

data class ProductResponse(
    val products: List<Product> = emptyList(),
    val total: Int = 0,
    val skip: Int = 0,
    val limit: Int = 0
)
