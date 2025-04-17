package com.devgun.quest1_hilt.data.remote.api

import com.devgun.quest1_hilt.data.remote.api.model.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostsAPI {
    @GET("posts")
    suspend fun getPosts(): Response<List<Posts>>
}