package com.richard.githubapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.richard.githubapp.core.data.remote.response.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}