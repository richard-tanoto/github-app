package com.richard.githubapp.core.data

import androidx.annotation.WorkerThread
import com.richard.githubapp.core.data.local.UserDao
import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.SearchUserResponse
import com.richard.githubapp.core.data.remote.response.UserDetailResponse
import com.richard.githubapp.core.data.remote.response.model.User
import com.richard.githubapp.core.data.remote.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
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

    fun getUserDetail(login: String): Flow<ApiResult<UserDetailResponse>> {
        return flow {
            try {
                val response = apiService.getUserDetail(login)
                emit(ApiResult.Success(response))
            } catch (exception: Exception) {
                emit(ApiResult.Error(exception.message))
            }
        }.onStart {
            emit(ApiResult.Loading())
        }.flowOn(Dispatchers.IO)
    }

    fun getUserFollowing(username: String): Flow<ApiResult<List<User>>> {
        return flow {
            try {
                val response = apiService.getUserFollowing(username)
                emit(ApiResult.Success(response))
            } catch (exception: Exception) {
                emit(ApiResult.Error(exception.message))
            }
        }.onStart {
            emit(ApiResult.Loading())
        }.flowOn(Dispatchers.IO)
    }

    fun getUserFollowers(username: String): Flow<ApiResult<List<User>>> {
        return flow {
            try {
                val response = apiService.getUserFollowers(username)
                emit(ApiResult.Success(response))
            } catch (exception: Exception) {
                emit(ApiResult.Error(exception.message))
            }
        }.onStart {
            emit(ApiResult.Loading())
        }.flowOn(Dispatchers.IO)
    }

    fun getFavoriteUsers(): Flow<List<User>> {
        return userDao.getUsers()
    }

    suspend fun isFavoriteUser(username: String) = userDao.isFavoriteUser(username)

    @WorkerThread
    suspend fun insertFavorite(user: User) {
        userDao.insertUser(user)
    }

    @WorkerThread
    suspend fun deleteFavorite(user: User) {
        userDao.deleteUser(user)
    }

}