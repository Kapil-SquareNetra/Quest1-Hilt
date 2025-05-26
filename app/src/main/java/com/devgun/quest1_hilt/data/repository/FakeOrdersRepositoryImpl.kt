package com.devgun.quest1_hilt.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.devgun.quest1_hilt.domain.orders.model.Order
import com.devgun.quest1_hilt.domain.orders.model.OrderItem
import com.devgun.quest1_hilt.domain.orders.model.User
import com.devgun.quest1_hilt.domain.repository.FakeOrdersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import java.time.LocalDateTime

class FakeOrdersRepositoryImpl(): FakeOrdersRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getAllOrders(): Flow<List<Order>> {
       return flow {
            emit(generateOrders())
        }
    }
}

val sampleUsers = listOf(
    User(userId = "U001", userName = "alice123"),
    User(userId = "U002", userName = "bob_the_builder"),
    User(userId = "U003", userName = "charlie89"),
    User(userId = "U004", userName = "diana.dev"),
    User(userId = "U005", userName = "eve_online")
)

val productCatalog = listOf(
    Triple("P001", BigDecimal("15.99"), "Soap"),
    Triple("P002", BigDecimal("42.50"), "Shampoo"),
    Triple("P003", BigDecimal("7.25"), "Sugar"),
    Triple("P004", BigDecimal("99"),"Apple"),
    Triple("P005", BigDecimal("3.49"),"Broccoli"),
    Triple("P006", BigDecimal("89.00"),"Detergent"),
    Triple("P007", BigDecimal("12.30"),"Deo"),
    Triple("P008", BigDecimal("5.99"),"Chocolates"),
    Triple("P009", BigDecimal("25.75"),"Rice"),
    Triple("P010", BigDecimal("60.00"),"Wheat")
)

fun randomOrderItem(): OrderItem {
    val (productId, price, productName) = productCatalog.random()
    val quantity = (1..5).random()
    return OrderItem(
        productId = productId,
        productName = productName,
        quantity = quantity,
        pricePerUnit = price
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun generateOrders(): List<Order> {
    val orders = mutableListOf<Order>()
    val now = LocalDateTime.now()

    repeat(30) { i ->
        val user = sampleUsers.random()
        val itemCount = (1..4).random()
        val items = List(itemCount) { randomOrderItem() }
        orders.add(
            Order(
                orderId = "ORD${i + 1}".padStart(5, '0'),
                userId = user.userId,
                items = items,
                timeStamp = now.minusDays((0..30).random().toLong()).minusHours((0..23).random().toLong())
            )
        )
    }

    return orders
}