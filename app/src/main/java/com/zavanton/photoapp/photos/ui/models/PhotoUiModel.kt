package com.zavanton.photoapp.photos.ui.models

import com.zavanton.photoapp.photos.business.models.PhotoBusinessModel

data class PhotoUiModel(
    val photoId: String,
    val imageLink: String,
    val description: String,
    val confidence: Double,
)

fun PhotoBusinessModel.toUiModel(): PhotoUiModel {
    return PhotoUiModel(
        photoId = id,
        imageLink = imageUrl,
        description = title,
        confidence = confidence,
    )
}
