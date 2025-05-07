package com.devgun.quest1_hilt.data.remote.api.products

import com.devgun.quest1_hilt.data.remote.api.products.model.Category
import com.devgun.quest1_hilt.data.remote.api.products.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsAPI {
    @GET("categories")
    suspend fun fetchCategories(): Response<List<Category>>

    @GET("search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("limit") limit: Int = 60
    ): Response<ProductResponse>
}