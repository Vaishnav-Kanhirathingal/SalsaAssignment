package com.example.salsa.models.home

data class HomeFeed(
    val creatorName: String,
    val creatorProfilePicUrl: String,
    val contentThumbnailUrl: String,
    val viewCount: Int,
    val diamondCount: Int
)