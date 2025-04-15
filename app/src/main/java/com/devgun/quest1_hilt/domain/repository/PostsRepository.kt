package com.devgun.quest1_hilt.domain.repository

import com.devgun.quest1_hilt.data.remote.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun fetchPostsAndStore(): Result<Unit>

    fun getAllPosts(): Flow<List<PostEntity>>

    suspend fun getPostById(id: Int): Result<PostEntity>

    suspend fun upsertAllPosts(list: List<PostEntity>)
}