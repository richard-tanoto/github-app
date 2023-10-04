package com.richard.githubapp.di

import com.richard.githubapp.core.data.UserRepository
import com.richard.githubapp.core.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService) = UserRepository(apiService)

}