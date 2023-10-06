package com.richard.githubapp

import android.app.UiModeManager
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.richard.githubapp.databinding.ActivityMainBinding
import com.richard.githubapp.util.GithubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTheme()?.let { setupTheme(it) }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupTheme(theme: GithubTheme) {
        when (theme) {
            GithubTheme.LIGHT -> {
                changeTheme(UiModeManager.MODE_NIGHT_NO, AppCompatDelegate.MODE_NIGHT_NO)
            }
            GithubTheme.DARK -> {
                changeTheme(UiModeManager.MODE_NIGHT_YES, AppCompatDelegate.MODE_NIGHT_YES)
            }
            GithubTheme.SYSTEM -> {
                changeTheme(UiModeManager.MODE_NIGHT_AUTO, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    private fun changeTheme(uiModeManagerMode: Int, appCompatDelegateMode: Int) {
        val uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            uiModeManager.setApplicationNightMode(uiModeManagerMode)
        } else {
            AppCompatDelegate.setDefaultNightMode(appCompatDelegateMode)
        }
    }

}