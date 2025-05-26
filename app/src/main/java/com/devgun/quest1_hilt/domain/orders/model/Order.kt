package com.devgun.quest1_hilt.domain.orders.model

import java.time.LocalDateTime

data class Order(
    val orderId: String,
    val userId: String,
    val items: List<OrderItem>,
    val timeStamp: LocalDateTime
)
