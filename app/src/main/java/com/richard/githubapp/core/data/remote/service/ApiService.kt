package com.richard.githubapp.core.data.remote.service

import com.richard.githubapp.core.data.remote.response.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}