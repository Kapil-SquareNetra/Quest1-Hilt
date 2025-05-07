package com.devgun.quest1_hilt.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devgun.quest1_hilt.data.remote.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Upsert
    suspend fun upsertAllProducts(products: List<ProductEntity>)

    @Query("Select * from ProductEntity where productName LIKE '%' || :query OR productDesc LIKE '%' || :query || '%'")
    fun getProductsByQuery(query: String): Flow<List<ProductEntity>>
}