package com.richard.githubapp.core.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.richard.githubapp.core.component.itemview.UserItemView
import com.richard.githubapp.core.data.remote.response.model.User
import com.richard.githubapp.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserItemView>() {

    private val list: ArrayList<User> = arrayListOf()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemView {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemView(binding)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserItemView, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(data) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setList(users: List<User>) {
        list.clear()
        list.addAll(users)
        notifyItemRangeChanged(0, list.lastIndex)
    }

}