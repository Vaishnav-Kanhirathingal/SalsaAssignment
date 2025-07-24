package com.example.salsa.models.home

data class HomeFeed(
    val creatorName: String,
    val creatorProfilePic: String,
    val contentThumbnailPic: String,
    val viewCount: Int,
    val diamondCount: Int
)