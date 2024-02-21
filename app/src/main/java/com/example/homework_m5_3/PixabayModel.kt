package com.example.homework_m5_3

data class PixabayModel(
    val total: Int,
    val totalHits: Int,
    val hits: List<ImageModel>
)

data class ImageModel(
    val largeImageURL: String,
    val likes: Int
)
