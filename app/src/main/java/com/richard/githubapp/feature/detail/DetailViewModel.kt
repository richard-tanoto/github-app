package com.richard.githubapp.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.richard.githubapp.core.data.UserRepository
import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.UserDetailResponse
import com.richard.githubapp.core.data.remote.response.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val login = MutableLiveData<String>()

    private val user = MutableLiveData<User>()

    val isFavorite = MutableLiveData<Boolean>()

    val userDetail: LiveData<ApiResult<UserDetailResponse>> = login.switchMap {
        userRepository.getUserDetail(it).asLiveData()
    }

    fun setLogin(login: String) {
        this.login.value = login
    }

    fun getLogin(): String? = login.value

    fun setUser(user: User) {
        this.user.value = user
    }

    fun getUser(): User? = user.value

    fun setFavorite(isFavorite: Boolean) {
        this.isFavorite.value = isFavorite
    }
    fun checkFavorite() = viewModelScope.launch {
        isFavorite.value = login.value?.let { userRepository.isFavoriteUser(it) }
    }

    fun insertFavorite() = viewModelScope.launch {
        user.value?.let { userRepository.insertFavorite(it) }
    }

    fun deleteFavorite() = viewModelScope.launch {
        user.value?.let { userRepository.deleteFavorite(it) }
    }

}