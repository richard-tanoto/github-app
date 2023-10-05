package com.richard.githubapp.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.richard.githubapp.core.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel() {

    private val username = MutableLiveData<String>()
    private val position = MutableLiveData<Int>()

    val userList = username.switchMap {
        if (position.value == 0) userRepository.getUserFollowing(it).asLiveData() else userRepository.getUserFollowers(it).asLiveData()
    }

    fun setUsername(username: String) {
        this.username.value = username
    }

    fun setPosition(position: Int) {
        this.position.value = position
    }

}