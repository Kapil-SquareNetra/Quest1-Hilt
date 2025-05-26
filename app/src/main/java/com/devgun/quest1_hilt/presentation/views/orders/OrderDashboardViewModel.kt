package com.devgun.quest1_hilt.presentation.views.orders

import androidx.lifecycle.ViewModel
import com.devgun.quest1_hilt.data.repository.sampleUsers
import com.devgun.quest1_hilt.domain.orders.model.User
import com.devgun.quest1_hilt.domain.repository.FakeOrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class OrderDashboardViewModel @Inject constructor(
    private val fakeOrdersRepository: FakeOrdersRepository
): ViewModel() {
    private val state = MutableStateFlow(OrderDashboardState())
    val uiState: StateFlow<OrderDashboardState> = state.asStateFlow()

    val allOrders = fakeOrdersRepository.getAllOrders()

    private val allItems = allOrders.map { orderList ->
        orderList.flatMap { it.items }
    }

    val totalRevenue: Flow<BigDecimal> = allOrders.map { orderList ->
        orderList.fold(BigDecimal.ZERO) { accumulatedOrders, order ->
            val orderTotal = order.items.fold(BigDecimal.ZERO) { accumulatedItems, item ->
                accumulatedItems + (item.pricePerUnit * item.quantity.toBigDecimal())
            }
            accumulatedOrders + orderTotal
        }
    }

    val mostExpensiveOrderItem = allItems.map { orderItems ->
        orderItems.reduce { accumulatedOrderItem, orderItem ->
            if (orderItem.pricePerUnit > accumulatedOrderItem.pricePerUnit) orderItem else accumulatedOrderItem
        }
    }.map {
        "${it.productName}@ Rs.${it.pricePerUnit}"
    }

    private val uniqueOrderItems = allItems.map { orderItems ->
        orderItems.distinctBy {
            it.productName
        }.toSet()
    }

    val uniqueItemsForDisplay = uniqueOrderItems.map {
        it.map { it.productName }.associateWith { null }
    }

    val productSalesCount = allItems.map { orderItems ->
        orderItems.groupBy { it.productName }
            .mapValues { (_, items) ->
                items.fold(BigDecimal.ZERO) { accumulatedItemCost, item ->
                    accumulatedItemCost + (item.pricePerUnit * item.quantity.toBigDecimal())
                }.toString()
            }
    }

    val userSpending = allOrders.map { orderItems ->
        orderItems.groupBy { it.userId }
            .mapKeys { (userId, _) ->
                sampleUsers.associate { it.userId to it.userName }[userId] ?: userId
            }
            .mapValues { (_, order) ->
                order.flatMap { it.items }
                    .fold(BigDecimal.ZERO) { accumulatedItemCost, item ->
                        accumulatedItemCost + (item.pricePerUnit * item.quantity.toBigDecimal())
                    }.toString()
            }
    }



}