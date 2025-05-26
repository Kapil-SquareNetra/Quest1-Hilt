package com.devgun.quest1_hilt.presentation.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListDataHolder(
    header: String,
    dataMap: Map<String, Any?>,
    itemsColor: Color,
    onItemClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = itemsColor)
    ) {
        Column(
            modifier = Modifier
                .widthIn(min = 80.dp, max = 175.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                text = header,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            for (item in dataMap) {
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = onItemClick
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.key,
                    )
                    item.value?.let {
                        Text(
                            text = it.toString(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListDataHolder() {
    Column(Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            ListDataHolder(
                header = "User spending",
                dataMap = mapOf(
                    "user001" to "10.00",
                    "user002" to "10.00",
                    "user003" to "10.00",
                    "user004" to "10.00",
                    "user005" to "10.00",
                    "user006" to "10.00",
                    "user007" to "10.00",
                    "user008" to "10.00",
                    "user009" to "10.00",
                    "user0011" to "10.00",
                ),
                itemsColor = Color.Red
            )
            ListDataHolder(
                header = "User spending",
                dataMap = mapOf(
                    "user001" to null,
                    "user002" to null,
                    "user003" to null,
                    "user004" to null,
                    "user005" to null,
                    "user006" to null,
                    "user007" to null,
                    "user008" to null,
                    "user009" to null,
                    "user0011" to null,
                ),
                itemsColor = Color.Red
            )
        }
    }
}
