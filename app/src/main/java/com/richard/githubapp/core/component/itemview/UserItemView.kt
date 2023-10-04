package com.richard.githubapp.core.component.itemview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.richard.githubapp.core.data.remote.response.model.User
import com.richard.githubapp.databinding.ItemUserBinding

class UserItemView(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: User) {
        binding.apply {
            tvName.text = data.login
            tvEmail.text = data.type
            Glide.with(binding.root.context)
                .load(data.avatarUrl)
                .into(civImage)
        }
    }

}