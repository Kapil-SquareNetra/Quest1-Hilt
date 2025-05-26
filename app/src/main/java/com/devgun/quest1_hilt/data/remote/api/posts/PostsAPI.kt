package com.devgun.quest1_hilt.data.remote.api.posts

import com.devgun.quest1_hilt.data.remote.api.posts.model.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostsAPI {
    @GET("posts")
    suspend fun getPosts(): Response<List<Posts>>
}