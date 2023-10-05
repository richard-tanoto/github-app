package com.richard.githubapp.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.richard.githubapp.core.data.remote.response.model.User

data class SearchUserResponse(
    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: List<User>,
)