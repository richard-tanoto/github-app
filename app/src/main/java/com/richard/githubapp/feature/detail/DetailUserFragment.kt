package com.richard.githubapp.feature.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.UserDetailResponse
import com.richard.githubapp.databinding.FragmentDetailUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailUserBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailUserFragmentArgs by navArgs()

    private var login: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.getLogin().isNullOrEmpty()) {
            setUsername()
        }
        setupToolbar()
        setupObserver()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = null
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setUsername() {
        login = args.username
        login?.let {
            viewModel.setLogin(it)
        } ?: run {
            showToast("Something's wrong. Please try again.")
        }
    }

    private fun setupObserver() {
        viewModel.userDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResult.Success -> {
                    showLoading(false)
                    setData(result.data)
                    setupViewPager(result.data)
                }

                is ApiResult.Loading -> {
                    showLoading(true)
                }

                is ApiResult.Error -> {
                    showLoading(false)
                    showToast(result.message)
                }
            }
        }
    }

    private fun setupViewPager(data: UserDetailResponse) {
        binding.tabs.visibility = VISIBLE
        val adapter = SectionPagerAdapter(this, data.login)
        binding.viewpager.adapter = adapter
        binding.viewpager.offscreenPageLimit = 3
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text =
                if (position == 0) "${data.following} Following" else "${data.followers} Followers"
        }.attach()
    }

    private fun setData(data: UserDetailResponse) {
        binding.apply {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = data.login
            if (!data.name.isNullOrEmpty()) {
                tvName.text = data.name
                tvName.visibility = VISIBLE
            }
            if (!data.email.isNullOrEmpty()) {
                tvEmail.text = data.email
                tvEmail.visibility = VISIBLE
            }
            if (!data.location.isNullOrEmpty()) {
                tvLocation.text = data.location
                tvLocation.visibility = VISIBLE
            }
            if (!data.company.isNullOrEmpty()) {
                tvCompany.text = data.company
                tvCompany.visibility = VISIBLE
            }
            Glide.with(this@DetailUserFragment)
                .load(data.avatarUrl)
                .into(imgUser)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) VISIBLE else GONE
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}