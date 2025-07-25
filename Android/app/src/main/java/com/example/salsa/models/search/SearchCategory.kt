package com.example.salsa.models.search

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SearchCategory(
    @SerializedName("profile_category_title") val profileCategoryTitle: String,
    @SerializedName("category_id") val categoryId: Int
)