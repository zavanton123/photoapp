package com.zavanton.photoapp.photos.data.repository

import com.zavanton.photoapp.photos.business.IPhotosRepository
import com.zavanton.photoapp.photos.business.models.PhotoBusinessModel
import javax.inject.Inject

class PhotosRepository @Inject constructor(

) : IPhotosRepository {

    override suspend fun downloadPhotos(): List<PhotoBusinessModel> {
        return listOf(
            PhotoBusinessModel("https://demo.com", "Title 1"),
            PhotoBusinessModel("https://demo.com", "Title 2"),
            PhotoBusinessModel("https://demo.com", "Title 3"),
        )
    }
}
