package com.richard.githubapp.core.data.remote.service

import com.richard.githubapp.core.data.remote.response.SearchUserResponse
import com.richard.githubapp.core.data.remote.response.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("search/users")
    suspend fun getUsersByQuery(
        @Query("q") query: String
    ): SearchUserResponse
}