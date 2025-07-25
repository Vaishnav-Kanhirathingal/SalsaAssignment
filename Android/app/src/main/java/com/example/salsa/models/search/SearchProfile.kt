package com.example.salsa.models.search

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SearchProfile(
    @SerializedName("profile_name") val profileName: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("diamond_count") val diamondCount: String,
    @SerializedName("view_count") val viewCount: Int,
)