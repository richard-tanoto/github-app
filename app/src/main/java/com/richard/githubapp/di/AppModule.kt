package com.richard.githubapp.di

import android.content.Context
import com.richard.githubapp.core.data.local.GithubPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGithubPreferencesManager(@ApplicationContext context: Context) = GithubPreferencesManager(context)

}