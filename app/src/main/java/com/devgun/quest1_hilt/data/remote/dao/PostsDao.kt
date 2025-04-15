package com.devgun.quest1_hilt.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devgun.quest1_hilt.data.remote.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {
    @Upsert
    suspend fun upsertAllPosts(posts: List<PostEntity>)

    @Query("Select * from PostEntity where id = :id")
    suspend fun getPostById(id: Int): PostEntity?

    @Query("Select * from PostEntity ORDER BY id ASC")
    fun getAllPosts(): Flow<List<PostEntity>>

}