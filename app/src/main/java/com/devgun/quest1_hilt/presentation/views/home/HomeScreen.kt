package com.devgun.quest1_hilt.presentation.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onProductClicked: () -> Unit,
    onPostsClicked:() -> Unit,
    onDashboardClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        Button(
            onClick = onProductClicked
        ) {
            Text(
                text = "Products"
            )
        }

        Button(
            onClick = onPostsClicked
        ) {
            Text(
                text = "Posts"
            )
        }

        Button(
            onClick = onDashboardClicked
        ) {
            Text(
                text = "Order Dashboard"
            )
        }
    }

}
