package com.example.salsa.models.home

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HomeFeed(
    @SerializedName("creator_name") val creatorName: String,
    @SerializedName("creator_profile_pic_url") val creatorProfilePicUrl: String,
    @SerializedName("content_thumbnail_url") val contentThumbnailUrl: String,
    @SerializedName("view_count") val viewCount: Int,
    @SerializedName("diamond_count") val diamondCount: Int
)