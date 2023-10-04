package com.richard.githubapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.richard.githubapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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
        setupOnClick()
        //TODO 1: Show user list
        //TODO 5: Implement search feature
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

    private fun setupOnClick() {
        binding.btnClick.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailUserFragment())
        }
    }

}