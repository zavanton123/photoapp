package com.zavanton.photoapp.photos.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zavanton.photoapp.photos.business.PhotoBusinessModel
import com.zavanton.photoapp.photos.data.db.PhotoDbModel


@JsonClass(generateAdapter = true)
data class PhotoApiModel(
    @Json(name = "_id")
    val photoId: String,

    @Json(name = "text")
    val description: String,

    @Json(name = "confidence")
    val confidence: Double,

    @Json(name = "image")
    val imageUrl: String,
)

fun PhotoApiModel.toBusinessModel(): PhotoBusinessModel {
    return PhotoBusinessModel(
        id = photoId,
        imageUrl = imageUrl,
        title = description,
        confidence = confidence
    )
}

fun PhotoApiModel.toDbModel(): PhotoDbModel {
    return PhotoDbModel(
        id = photoId,
        imageUrl = imageUrl,
        title = description,
        confidence = confidence
    )
}
