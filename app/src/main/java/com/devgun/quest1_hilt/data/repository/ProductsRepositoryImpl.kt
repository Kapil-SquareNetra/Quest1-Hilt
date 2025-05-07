package com.devgun.quest1_hilt.data.repository

import android.util.Log
import com.devgun.quest1_hilt.data.remote.api.products.ProductsAPI
import com.devgun.quest1_hilt.data.remote.api.products.model.Category
import com.devgun.quest1_hilt.data.remote.api.products.model.Product
import com.devgun.quest1_hilt.data.remote.dao.CategoryDao
import com.devgun.quest1_hilt.data.remote.dao.ProductsDao
import com.devgun.quest1_hilt.data.remote.entity.CategoryEntity
import com.devgun.quest1_hilt.data.remote.entity.ProductEntity
import com.devgun.quest1_hilt.domain.repository.ProductsRepository
import com.devgun.quest1_hilt.runSuspendCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import java.util.UUID
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsAPI: ProductsAPI,
    private val productsDao: ProductsDao,
    private val categoryDao: CategoryDao
) : ProductsRepository {
    override suspend fun fetchCategoriesAndStore(): Result<Unit> {
        if (categoryDao.getCategoryCount() > 0) return Result.success(Unit)
        return productsAPI.runSuspendCatching {
            fetchCategories()
        }.mapCatching { response ->
            Log.i("kapilDebug", "response: $response")
            if (response.isSuccessful) {
                val categories = response.body().orEmpty().map {
                    it.mapToCategoryEntity()
                }
                if (categories.isEmpty()) {
                    throw Exception("Categories response body is null or empty")
                } else {
                    categoryDao.upsertAllCategories(
                        categories
                    )
                }
            } else {
                throw Exception("fetch categories failed")
            }

        }
    }

    override suspend fun getAllCategories(): List<CategoryEntity> {
        return categoryDao.getAllCategories()
    }

    override fun searchProducts(query: String): Flow<List<ProductEntity>> {
        return merge(
            searchProductsFromAPI(query),
            searchProductsFromDB(query)
        )
    }

    private fun searchProductsFromAPI(query: String): Flow<List<ProductEntity>> {
        return flow {
            val response = productsAPI.searchProducts(query)
            if (response.isSuccessful) {
                val productList =
                    response.body()?.products?.map { it.mapToProductEntity() }.orEmpty()
                if (productList.isNotEmpty()) {
                    productsDao.upsertAllProducts(productList)
                }
                emit(productList)
            }
        }
    }

    private fun searchProductsFromDB(query: String): Flow<List<ProductEntity>> {
        return productsDao.getProductsByQuery(query)
    }

    private fun Category.mapToCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            id = UUID.randomUUID().toString(),
            categoryName = name,
            url = url
        )
    }

    private fun Product.mapToProductEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            productName = title,
            productDesc = description,
            category = category,
            cost = price,
            productImage = thumbnail
        )
    }
}