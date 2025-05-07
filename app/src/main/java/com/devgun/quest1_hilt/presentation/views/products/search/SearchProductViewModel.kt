package com.devgun.quest1_hilt.presentation.views.products.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgun.quest1_hilt.data.remote.entity.CategoryEntity
import com.devgun.quest1_hilt.data.remote.entity.ProductEntity
import com.devgun.quest1_hilt.domain.repository.ProductsRepository
import com.devgun.quest1_hilt.presentation.ui.models.CategoryModel
import com.devgun.quest1_hilt.presentation.ui.models.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _searchQueryState = MutableStateFlow("")
    internal val searchQueryState: StateFlow<String> = _searchQueryState.asStateFlow()

    private val _categoryState = MutableStateFlow<List<CategoryModel>>(emptyList())
    internal val categoryState: StateFlow<List<CategoryModel>> = _categoryState.asStateFlow()

    private val _sortASCOrderState = MutableStateFlow(true)
    internal val sortASCOrderState: StateFlow<Boolean> = _sortASCOrderState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchedProductList: StateFlow<List<ProductModel>> = combine(
        _searchQueryState.debounce(300).distinctUntilChanged(),
        _categoryState,
        _sortASCOrderState
    ) { searchQuery, selectedCategories, isSortAsc ->
        SearchProductState(
            searchQuery,
            selectedCategories.filter { it.isSwitchedOn },
            isSortAsc
        )
    }.flatMapLatest { state ->
        productsRepository.searchProducts(state.searchQuery).map { products ->
            val categoryFiltered = if (state.selectedCategories.isEmpty()) {
                products
            } else {
                products.filter { product ->
                    state.selectedCategories.any { category ->
                        val keyword = category.categoryName
                        product.productDesc.contains(keyword, ignoreCase = true)
                                || product.productName.contains(keyword, ignoreCase = true)
                                || product.category.contains(keyword, ignoreCase = true)
                    }
                }
            }
            val sortedList = if (state.sortAscending) {
                categoryFiltered.sortedBy { it.productName }
            } else {
                categoryFiltered.sortedByDescending { it.productName }
            }
            sortedList.map {
                it.mapToProductModel()
            }

        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )


    init {
        viewModelScope.launch {
            val categories = productsRepository.getAllCategories()
            _categoryState.update {
                categories.map {
                    it.mapToCategoryModel()
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        _searchQueryState.update {
            query
        }
    }

    fun onSortOrderChanged() {
        _sortASCOrderState.update {
            !_sortASCOrderState.value
        }
    }

    fun onRefresh(){
        _sortASCOrderState.update {
            true
        }
        _searchQueryState.update {
            ""
        }
        _categoryState.update {
            it.map { category ->
                category.copy(
                    isSwitchedOn = false
                )
            }
        }
    }

    fun onCategoryChanged(categoryModel: CategoryModel) {
        _categoryState.update {
            it.map { category ->
                if (category.categoryName == categoryModel.categoryName) {
                    categoryModel.copy(
                        isSwitchedOn = !categoryModel.isSwitchedOn
                    )
                } else {
                    category
                }
            }
        }
    }


    private fun ProductEntity.mapToProductModel(): ProductModel {
        return ProductModel(
            id = id,
            productInfo = "$productName at Rs. $cost",
            productDescription = productDesc,
            productImage = productImage
        )
    }


    private fun CategoryEntity.mapToCategoryModel(): CategoryModel {
        return CategoryModel(
            categoryName = categoryName,
        )
    }

}