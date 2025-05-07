package com.devgun.quest1_hilt.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = searchQuery,
        onValueChange = {
            onValueChanged(it)
        },
        shape = RoundedCornerShape(12.dp),
        leadingIcon = {
            Image(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotBlank()) {
                Image(
                    modifier = Modifier.clickable {
                        onValueChanged("")
                    },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewSearchBar(){
    SearchBar() {

    }
}