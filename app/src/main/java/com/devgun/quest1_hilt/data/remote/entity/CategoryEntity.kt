package com.devgun.quest1_hilt.data.remote.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey val id: String,
    val categoryName: String,
    val url: String
)
