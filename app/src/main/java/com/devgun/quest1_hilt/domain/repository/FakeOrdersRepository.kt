package com.devgun.quest1_hilt.domain.repository

import com.devgun.quest1_hilt.domain.orders.model.Order
import kotlinx.coroutines.flow.Flow

interface FakeOrdersRepository {
    fun getAllOrders(): Flow<List<Order>>
}