package com.devgun.quest1_hilt.presentation.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devgun.quest1_hilt.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            productsRepository.fetchCategoriesAndStore()
        }
    }
}