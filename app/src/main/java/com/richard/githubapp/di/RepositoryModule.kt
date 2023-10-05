package com.richard.githubapp.di

import android.content.Context
import androidx.room.Room
import com.richard.githubapp.core.data.UserRepository
import com.richard.githubapp.core.data.local.UserDao
import com.richard.githubapp.core.data.local.UserDatabase
import com.richard.githubapp.core.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService, userDao: UserDao) = UserRepository(apiService, userDao)

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, UserDatabase::class.java, "users.db").build()

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao()

}