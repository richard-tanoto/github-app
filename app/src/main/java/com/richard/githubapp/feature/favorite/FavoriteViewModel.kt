package com.richard.githubapp.feature.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.richard.githubapp.core.data.UserRepository
import com.richard.githubapp.core.data.remote.response.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(userRepository: UserRepository): ViewModel() {

    val favoriteUsers: LiveData<List<User>> = userRepository.getFavoriteUsers().asLiveData()

}