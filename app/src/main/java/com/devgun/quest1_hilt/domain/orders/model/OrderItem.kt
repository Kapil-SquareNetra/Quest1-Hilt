package com.devgun.quest1_hilt.domain.orders.model

import java.math.BigDecimal

data class OrderItem(
    val productId: String,
    val productName: String,
    val quantity: Int,
    val pricePerUnit: BigDecimal
)
