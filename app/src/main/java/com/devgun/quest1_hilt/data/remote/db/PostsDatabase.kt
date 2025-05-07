package com.devgun.quest1_hilt.data.remote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devgun.quest1_hilt.data.remote.dao.PostsDao
import com.devgun.quest1_hilt.data.remote.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1
)

abstract class PostsDatabase: RoomDatabase() {
    abstract val postsDao: PostsDao
}