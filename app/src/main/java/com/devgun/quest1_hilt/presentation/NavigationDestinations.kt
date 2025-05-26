package com.devgun.quest1_hilt.presentation

import kotlinx.serialization.Serializable

@Serializable
object HomePage

@Serializable
object PostListing

@Serializable
data class PostOverview(
    val postId: Int
)

@Serializable
object SearchProduct

@Serializable
object OrderDashboard