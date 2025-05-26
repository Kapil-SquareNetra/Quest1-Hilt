package com.devgun.quest1_hilt.presentation.views.orders

data class OrderDashboardState(
    val totalRevenue: String = "0.0",
    val mostExpensiveItem: String = "",
    val frequentlyBought: Set<String> = emptySet(),
    val productSalesCount: Map<String, Int> = emptyMap(),
    val userSpending: Map<String, Double> = emptyMap(),
    val userLoyaltyPoints: List<Int> = emptyList()
)
