package com.zavanton.photoapp.photos.business

data class PhotoBusinessModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val confidence: Double,
)
