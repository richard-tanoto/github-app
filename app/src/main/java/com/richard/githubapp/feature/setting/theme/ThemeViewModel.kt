package com.richard.githubapp.feature.setting.theme

import androidx.lifecycle.ViewModel
import com.richard.githubapp.core.data.local.GithubPreferencesManager
import com.richard.githubapp.util.GithubTheme
import com.richard.githubapp.util.KeyConstants.THEME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val githubPreferencesManager: GithubPreferencesManager
): ViewModel() {

    fun getTheme() = githubPreferencesManager.getString(THEME_KEY)?.let { GithubTheme.valueOf(it) }

    fun saveTheme(theme: String) = githubPreferencesManager.saveString(THEME_KEY, theme)

}