package com.devgun.quest1_hilt.data.remote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devgun.quest1_hilt.data.remote.dao.CategoryDao
import com.devgun.quest1_hilt.data.remote.dao.ProductsDao
import com.devgun.quest1_hilt.data.remote.entity.CategoryEntity
import com.devgun.quest1_hilt.data.remote.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, CategoryEntity::class],
    version = 1
)

abstract class ProductsDatabase : RoomDatabase() {
    abstract val productsDao: ProductsDao
    abstract val categoryDao: CategoryDao
}