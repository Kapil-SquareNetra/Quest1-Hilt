package com.devgun.quest1_hilt.di

import com.devgun.quest1_hilt.data.repository.PostsRepositoryImpl
import com.devgun.quest1_hilt.data.repository.ProductsRepositoryImpl
import com.devgun.quest1_hilt.domain.repository.PostsRepository
import com.devgun.quest1_hilt.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostsRepository(
        postsRepository: PostsRepositoryImpl
    ): PostsRepository

    @Binds
    @Singleton
    abstract fun bindProductsRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository

}