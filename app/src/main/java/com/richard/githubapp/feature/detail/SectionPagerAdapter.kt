package com.richard.githubapp.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(fragment: Fragment, private val login: String) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = ListUserFragment()
        fragment.arguments = Bundle().apply {
            putString("login", login)
            putInt("position", position)
        }
        return fragment
    }
}