package com.richard.githubapp.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.richard.githubapp.core.data.UserRepository
import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.SearchUserResponse
import com.richard.githubapp.core.data.remote.response.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val query = MutableLiveData<String>()

    val result: LiveData<ApiResult<List<User>>> = userRepository.getUsers().asLiveData()

    val searchResult: LiveData<ApiResult<SearchUserResponse>> = query.switchMap {
        userRepository.getUsersByQuery(it).asLiveData()
    }

    fun setQuery(query: String) {
        this.query.value = query
    }

}