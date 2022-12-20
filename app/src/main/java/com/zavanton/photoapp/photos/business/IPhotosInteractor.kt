package com.zavanton.photoapp.photos.business

import com.zavanton.photoapp.photos.business.models.PhotoBusinessModel

interface IPhotosInteractor {

    suspend fun downloadPhotos(): List<PhotoBusinessModel>
}