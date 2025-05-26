package com.devgun.quest1_hilt.presentation.views.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devgun.quest1_hilt.R
import com.devgun.quest1_hilt.data.repository.sampleUsers
import com.devgun.quest1_hilt.domain.orders.model.OrderItem
import com.devgun.quest1_hilt.presentation.ui.composables.AggregateDataHolder
import com.devgun.quest1_hilt.presentation.ui.composables.CommonHeader
import com.devgun.quest1_hilt.presentation.ui.composables.ListDataHolder
import java.math.BigDecimal

@Composable
fun OrderDashboardScreen(
    viewModel: OrderDashboardViewModel,
    onBackClick: () -> Unit
) {
    val totalRevenue by viewModel.totalRevenue.collectAsState(BigDecimal.ZERO)
    val mostExpensiveOrderItem by viewModel.mostExpensiveOrderItem.collectAsState("")
    val uniqueProducts by viewModel.uniqueItemsForDisplay.collectAsState(emptyMap())
    val productSales by viewModel.productSalesCount.collectAsState(emptyMap())
    val userSpendSummary by viewModel.userSpending.collectAsState(emptyMap())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonHeader(
            headerText = "Order Dashboard",
            leadingIcon = R.drawable.back_arrow,
            leadingIconClick = onBackClick
        )

        AggregateDataHolder(
            header = "Total Revenue",
            value = totalRevenue.toString(),
            valueColor = Color.Blue
        )
        AggregateDataHolder(
            header = "Most Expensive Item",
            value = mostExpensiveOrderItem,
            valueColor = Color.Red
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ListDataHolder(
                header = "Unique Products",
                dataMap = uniqueProducts,
                itemsColor = Color.Green
            )
            ListDataHolder(
                header = "Product Sales",
                dataMap = productSales,
                itemsColor = Color.Yellow
            )
        }

        ListDataHolder(
            header = "User Spend Summary",
            dataMap = userSpendSummary,
            itemsColor = Color.Blue
        )


    }
}