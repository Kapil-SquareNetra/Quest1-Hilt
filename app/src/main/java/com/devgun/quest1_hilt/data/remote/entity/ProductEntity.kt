package com.devgun.quest1_hilt.data.remote.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey val id: Int,
    val productName: String,
    val productDesc: String,
    val cost: Double,
    val category: String,
    val productImage: String
)
