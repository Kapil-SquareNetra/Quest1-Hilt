package com.devgun.quest1_hilt.di

import android.app.Application
import androidx.room.Room
import com.devgun.quest1_hilt.data.remote.api.posts.PostsAPI
import com.devgun.quest1_hilt.data.remote.api.products.ProductsAPI
import com.devgun.quest1_hilt.data.remote.dao.CategoryDao
import com.devgun.quest1_hilt.data.remote.dao.PostsDao
import com.devgun.quest1_hilt.data.remote.dao.ProductsDao
import com.devgun.quest1_hilt.data.remote.db.PostsDatabase
import com.devgun.quest1_hilt.data.remote.db.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostsAPI(): PostsAPI {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(PostsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsAPI(): ProductsAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummyjson.com/products/")
            .build()
            .create(ProductsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePostsDatabase(context: Application): PostsDatabase {
        return Room.databaseBuilder(
            context,
            PostsDatabase::class.java,
            "post_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductsDatabase(context: Application): ProductsDatabase {
        return Room.databaseBuilder(
            context,
            ProductsDatabase::class.java,
            "products_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostsDao(database: PostsDatabase): PostsDao {
        return database.postsDao
    }

    @Provides
    @Singleton
    fun provideProductsDao(database: ProductsDatabase): ProductsDao {
        return database.productsDao
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: ProductsDatabase): CategoryDao {
        return database.categoryDao
    }

}