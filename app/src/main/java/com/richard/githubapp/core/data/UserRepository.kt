package com.richard.githubapp.core.data

import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.SearchUserResponse
import com.richard.githubapp.core.data.remote.response.model.User
import com.richard.githubapp.core.data.remote.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getUsers(): Flow<ApiResult<List<User>>> {
        return flow {
            try {
                val response = apiService.getUsers()
                emit(ApiResult.Success(response))
            } catch (exception: Exception) {
                emit(ApiResult.Error(exception.message))
            }
        }.onStart {
            emit(ApiResult.Loading())
        }.flowOn(Dispatchers.IO)
    }

    fun getUsersByQuery(query: String): Flow<ApiResult<SearchUserResponse>> {
        return flow {
            emit(ApiResult.Loading())
            try {
                val response = apiService.getUsersByQuery(query)
                emit(ApiResult.Success(response))
            } catch (exception: Exception) {
                emit(ApiResult.Error(exception.message))
            }
        }.flowOn(Dispatchers.IO)
    }

}