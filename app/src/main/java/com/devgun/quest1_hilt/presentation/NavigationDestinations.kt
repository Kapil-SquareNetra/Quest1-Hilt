package com.devgun.quest1_hilt.presentation

import kotlinx.serialization.Serializable

@Serializable
object PostListing

@Serializable
data class PostOverview(
    val postId: Int
)