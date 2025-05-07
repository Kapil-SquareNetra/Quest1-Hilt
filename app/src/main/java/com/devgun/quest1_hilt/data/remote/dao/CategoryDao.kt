package com.devgun.quest1_hilt.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devgun.quest1_hilt.data.remote.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Upsert
    suspend fun upsertAllCategories(categories: List<CategoryEntity>)

    @Query("Select * from categoryentity")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("Select Count(*) from categoryentity")
    suspend fun getCategoryCount(): Int


}