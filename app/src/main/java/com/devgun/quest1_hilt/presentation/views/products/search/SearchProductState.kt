package com.devgun.quest1_hilt.presentation.views.products.search

import com.devgun.quest1_hilt.presentation.ui.models.CategoryModel

data class SearchProductState(
    val searchQuery: String = "",
    val selectedCategories: List<CategoryModel> = emptyList(),
    val sortAscending: Boolean = true,
)
