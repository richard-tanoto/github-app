package com.richard.githubapp.feature.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.richard.githubapp.R
import com.richard.githubapp.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setOptionClickListener()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = getString(R.string.setting)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setOptionClickListener() {
        binding.layoutTheme.setOnClickListener {
            findNavController().navigate(SettingFragmentDirections.actionSettingFragmentToThemeFragment())
        }
    }

}