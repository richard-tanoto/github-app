package com.richard.githubapp.feature.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.richard.githubapp.R
import com.richard.githubapp.core.component.adapter.UserAdapter
import com.richard.githubapp.core.data.remote.response.model.User
import com.richard.githubapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupObserver()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = getString(R.string.favorite)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.apply {
            userAdapter = UserAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
            userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    findNavController().navigate(
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailUserFragment(data)
                    )
                }
            })
        }
    }

    private fun setupObserver() {
        viewModel.favoriteUsers.observe(viewLifecycleOwner) { favorites ->
            if (favorites.isEmpty()) {
                showToast("No favorite users available.")
            } else {
                userAdapter.setList(favorites)
            }
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}