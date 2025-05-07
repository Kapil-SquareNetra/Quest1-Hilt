package com.devgun.quest1_hilt.presentation.views.products.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devgun.quest1_hilt.R
import com.devgun.quest1_hilt.presentation.ui.composables.CategoryItem
import com.devgun.quest1_hilt.presentation.ui.composables.CommonHeader
import com.devgun.quest1_hilt.presentation.ui.composables.ProductItem
import com.devgun.quest1_hilt.presentation.ui.composables.SearchBar
import kotlinx.coroutines.launch

@Composable
fun SearchProductScreen(
    onBackClick: () -> Unit,
    viewModel: SearchProductViewModel,
){
    val searchQuery by viewModel.searchQueryState.collectAsState()
    val sortInASC by viewModel.sortASCOrderState.collectAsState()
    val categories by viewModel.categoryState.collectAsState()
    val productList by viewModel.searchedProductList.collectAsState()
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonHeader(
            headerText = "Search Product",
            leadingIcon = R.drawable.close,
            leadingIconClick = onBackClick,
            trailingComposable = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            viewModel.onRefresh()
                        },
                        painter = painterResource(R.drawable.refresh) ,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Row(
                        modifier = Modifier
                            .clickable {
                            viewModel.onSortOrderChanged()
                            scope.launch {
                                if (productList.isNotEmpty()) {
                                    scrollState.scrollToItem(0)
                                }
                            }
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.sort_alpha ),
                            contentDescription = null,
                            tint = Color.Black
                        )
                        Icon(
                            modifier = Modifier.size(16.dp).offset(x = (-4).dp),
                            painter = painterResource(if (sortInASC) R.drawable.sort_desc else R.drawable.sort_asc),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }

            }
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item { Spacer(modifier = Modifier.width(16.dp)) }
            itemsIndexed(items = categories , key = { _, category -> category.categoryName}) { _, category ->
                CategoryItem(
                    categoryModel = category,
                    onCategoryClicked = {
                        viewModel.onCategoryChanged(category)
                    }
                )
            }

        }
        SearchBar(
            searchQuery = searchQuery
        ) {
            viewModel.onQueryChanged(it)
        }
        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            itemsIndexed(items = productList , key = { _, product -> product.id}) { _, product ->
                ProductItem(
                    product = product
                )
            }

        }
    }
}