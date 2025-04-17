package com.devgun.quest1_hilt.data.repository

import android.util.Log
import com.devgun.quest1_hilt.data.remote.api.PostsAPI
import com.devgun.quest1_hilt.data.remote.api.model.Posts
import com.devgun.quest1_hilt.data.remote.dao.PostsDao
import com.devgun.quest1_hilt.data.remote.entity.PostEntity
import com.devgun.quest1_hilt.domain.repository.PostsRepository
import com.devgun.quest1_hilt.runSuspendCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsAPI: PostsAPI,
    private val postsDao: PostsDao
): PostsRepository {
    override suspend fun fetchPostsAndStore(): Result<Unit> {
        Log.i("kapil", "fetchPostsAndStore()")
        return postsAPI.runSuspendCatching {
            getPosts()
        }.mapCatching { response ->
            Log.i("kapil", response.toString())
            if (response.isSuccessful) {
                val posts = response.body().orEmpty().map { it.mapToPostsEntity() }
                if (posts.isEmpty()) {
                    throw Exception("Posts response body is null or empty")
                } else {
                    upsertAllPosts(posts)
                }
            } else {
                throw Exception("fetch posts failed")
            }
        }
    }

    override fun getAllPosts(): Flow<List<PostEntity>> {
        return postsDao.getAllPosts()
    }


    override suspend fun getPostById(id: Int): Result<PostEntity> {
        return postsDao.getPostById(id)?.let {
            Result.success(it)
        } ?: Result.failure(Exception("No post with id:$id found!"))
    }

    override suspend fun upsertAllPosts(list: List<PostEntity>) {
        postsDao.upsertAllPosts(list)
    }

    private fun Posts.mapToPostsEntity(): PostEntity {
        return PostEntity(
            id = id,
            userId = userId,
            title = title,
            body = body
        )
    }
}