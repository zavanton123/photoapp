package com.zavanton.photoapp.photos.business

import com.zavanton.photoapp.photos.business.models.PhotoBusinessModel
import javax.inject.Inject

class PhotosInteractor @Inject constructor(
) : IPhotosInteractor {

    override suspend fun downloadPhotos(): List<PhotoBusinessModel> {
        return listOf(
            PhotoBusinessModel("https://demo.com", "Title 1"),
            PhotoBusinessModel("https://demo.com", "Title 2"),
            PhotoBusinessModel("https://demo.com", "Title 3"),
        )
    }
}
