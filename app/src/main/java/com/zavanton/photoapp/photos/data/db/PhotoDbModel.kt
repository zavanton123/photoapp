package com.zavanton.photoapp.photos.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zavanton.photoapp.photos.business.PhotoBusinessModel

@Entity(tableName = "photo")
data class PhotoDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "confidence")
    val confidence: Double,
)

fun PhotoDbModel.toBusinessModel(): PhotoBusinessModel {
    return PhotoBusinessModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        confidence = confidence,
    )
}
