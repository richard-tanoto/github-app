package com.richard.githubapp.core.data.remote.service

import com.richard.githubapp.core.data.remote.response.SearchUserResponse
import com.richard.githubapp.core.data.remote.response.UserDetailResponse
import com.richard.githubapp.core.data.remote.response.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("search/users")
    suspend fun getUsersByQuery(
        @Query("q") query: String
    ): SearchUserResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") login: String
    ): UserDetailResponse

    @GET("users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") login: String
    ): List<User>

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") login: String
    ): List<User>
}