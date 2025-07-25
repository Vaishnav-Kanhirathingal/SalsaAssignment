package com.example.salsa.models.profile

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class UserProfile(
    @SerializedName("user_name") val userName: String,
    @SerializedName("user_full_name") val userFullName: String,
    @SerializedName("profile_pic_url") val profilePicURL: String,
    @SerializedName("diamonds_earned") val diamondsEarned: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int,
    @SerializedName("posts_base_url") val postsBaseURL: String,
)