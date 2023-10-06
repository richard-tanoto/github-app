package com.richard.githubapp.feature.setting.theme

import android.app.UiModeManager
import android.content.Context.UI_MODE_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.richard.githubapp.R
import com.richard.githubapp.databinding.FragmentThemeBinding
import com.richard.githubapp.util.GithubTheme
import com.richard.githubapp.util.GithubTheme.DARK
import com.richard.githubapp.util.GithubTheme.LIGHT
import com.richard.githubapp.util.GithubTheme.SYSTEM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeFragment : Fragment() {

    private lateinit var binding: FragmentThemeBinding
    private val viewModel: ThemeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setSelectedRadioButton()
        setOnRadioClickListener()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = getString(R.string.theme)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setSelectedRadioButton() {
        val theme = viewModel.getTheme()
        theme?.let { githubTheme ->
            when (githubTheme) {
                LIGHT -> binding.radioGroup.check(binding.btnLightMode.id)
                DARK -> binding.radioGroup.check(binding.btnDarkMode.id)
                SYSTEM -> binding.radioGroup.check(binding.btnDeviceDefault.id)
            }
        }
    }

    private fun setOnRadioClickListener() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.btnLightMode.id -> {
                    changeAndSaveTheme(LIGHT)
                }

                binding.btnDarkMode.id -> {
                    changeAndSaveTheme(DARK)
                }

                binding.btnDeviceDefault.id -> {
                    changeAndSaveTheme(SYSTEM)
                }
            }
        }
    }

    private fun changeAndSaveTheme(theme: GithubTheme) {
        when (theme) {
            LIGHT -> {
                changeTheme(UiModeManager.MODE_NIGHT_NO, AppCompatDelegate.MODE_NIGHT_NO)
            }
            DARK -> {
                changeTheme(UiModeManager.MODE_NIGHT_YES, AppCompatDelegate.MODE_NIGHT_YES)
            }
            SYSTEM -> {
                changeTheme(UiModeManager.MODE_NIGHT_AUTO, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
        viewModel.saveTheme(theme.name)
    }

    private fun changeTheme(uiModeManagerMode: Int, appCompatDelegateMode: Int) {
        val uiModeManager = requireActivity().getSystemService(UI_MODE_SERVICE) as UiModeManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            uiModeManager.setApplicationNightMode(uiModeManagerMode)
        } else {
            AppCompatDelegate.setDefaultNightMode(appCompatDelegateMode)
        }
    }

}