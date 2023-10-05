package com.richard.githubapp.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.richard.githubapp.core.data.UserRepository
import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.UserDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val login = MutableLiveData<String>()

    val userDetail: LiveData<ApiResult<UserDetailResponse>> = login.switchMap {
        userRepository.getUserDetail(it).asLiveData()
    }

    fun setLogin(login: String) {
        this.login.value = login
    }

}