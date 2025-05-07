package com.devgun.quest1_hilt.domain.repository

import com.devgun.quest1_hilt.data.remote.entity.CategoryEntity
import com.devgun.quest1_hilt.data.remote.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun fetchCategoriesAndStore(): Result<Unit>

    suspend fun getAllCategories(): List<CategoryEntity>

    fun searchProducts(query: String): Flow<List<ProductEntity>>


}