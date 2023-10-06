package com.richard.githubapp

import androidx.lifecycle.ViewModel
import com.richard.githubapp.core.data.local.GithubPreferencesManager
import com.richard.githubapp.util.GithubTheme
import com.richard.githubapp.util.KeyConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val githubPreferencesManager: GithubPreferencesManager
): ViewModel() {

    fun getTheme() = githubPreferencesManager.getString(KeyConstants.THEME_KEY)?.let { GithubTheme.valueOf(it) }

}