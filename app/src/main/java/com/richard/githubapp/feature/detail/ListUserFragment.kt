package com.richard.githubapp.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.richard.githubapp.core.component.adapter.UserAdapter
import com.richard.githubapp.core.data.remote.response.ApiResult
import com.richard.githubapp.core.data.remote.response.model.User
import com.richard.githubapp.databinding.FragmentListUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUserFragment: Fragment() {

    private lateinit var binding: FragmentListUserBinding
    private val viewModel: ListUserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.getUsername().isNullOrEmpty()) {
            setData()
        }
        setupRecyclerView()
        setupObserver()
    }

    private fun setData() {
        val login = arguments?.getString("login")
        login?.let {
            viewModel.setUsername(login)
        }
        val position = arguments?.getInt("position")
        position?.let {
            viewModel.setPosition(position)
        }
        if (position == null || login == null) {
            showToast("Something's wrong! Please try again.")
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            userAdapter = UserAdapter()
            adapter = userAdapter
            userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    findNavController().navigate(DetailUserFragmentDirections.actionDetailUserFragmentSelf(data.login))
                }
            })
        }
    }

    private fun setupObserver() {
        viewModel.userList.observe(viewLifecycleOwner) { result ->
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
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}