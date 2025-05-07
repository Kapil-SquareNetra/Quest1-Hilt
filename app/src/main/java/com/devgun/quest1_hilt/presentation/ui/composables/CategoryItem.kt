package com.devgun.quest1_hilt.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devgun.quest1_hilt.presentation.ui.models.CategoryModel

@Composable
fun CategoryItem(
    categoryModel: CategoryModel,
    onCategoryClicked: () -> Unit
) {
    val backgroundColor = if (categoryModel.isSwitchedOn) Color.Red else Color.White
    Box(
        modifier = Modifier
            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable { onCategoryClicked() },
            text = categoryModel.categoryName,
        )
    }

}

