package com.zavanton.photoapp.photos.business.models

data class PhotoBusinessModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val confidence: Double,
)
