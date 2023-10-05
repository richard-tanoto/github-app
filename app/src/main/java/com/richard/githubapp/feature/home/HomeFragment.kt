package com.richard.githubapp.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.richard.githubapp.R
import com.richard.githubapp.core.component.adapter.UserAdapter
import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.model.User
import com.richard.githubapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private lateinit var searchUserAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupObserver()
        setupSearchQueryListener()
    }

    private fun setupToolbar() {
        binding.searchBar.apply {
            inflateMenu(R.menu.home_toolbar_menu)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.destFavorite -> {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFavoriteFragment())
                        true
                    }
                    R.id.destSetting -> {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingFragment())
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.apply {
            userAdapter = UserAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
            userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    showToast(data.login)
                }
            })
        }
        binding.searchRvUser.apply {
            searchUserAdapter = UserAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchUserAdapter
            searchUserAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    showToast(data.login)
                }
            })
        }
    }

    private fun setupObserver() {
        viewModel.result.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResult.Success -> {
                    showLoading(false)
                    userAdapter.setList(result.data)
                }
                is ApiResult.Loading -> {
                    showLoading(true)
                    userAdapter.clearList()
                }
                is ApiResult.Error -> {
                    showLoading(false)
                    showToast(result.message)
                }
            }
        }

        viewModel.searchResult.observe(viewLifecycleOwner) { searchResult ->
            when (searchResult) {
                is ApiResult.Success -> {
                    showSearchLoading(false)
                    searchUserAdapter.setList(searchResult.data.items)
                }
                is ApiResult.Loading -> {
                    showSearchLoading(true)
                    searchUserAdapter.clearList()
                }
                is ApiResult.Error -> {
                    showSearchLoading(false)
                    showToast(searchResult.message)
                }
            }
        }
    }

    private fun setupSearchQueryListener() {
        binding.searchView.editText.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                Log.d(HomeFragment::class.simpleName, text.toString())
                if (text?.length != 0) viewModel.setQuery(text.toString())
            }
        )
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) VISIBLE else GONE
    }

    private fun showSearchLoading(isLoading: Boolean) {
        binding.searchProgressBar.visibility = if (isLoading) VISIBLE else GONE
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}