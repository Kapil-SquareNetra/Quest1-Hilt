package com.devgun.quest1_hilt.data.remote.api.posts.model

data class Posts(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)