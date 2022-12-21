package com.zavanton.photoapp.photos.business

interface IPhotosInteractor {

    suspend fun downloadPhotos(maxPhotoId: String?): List<PhotoBusinessModel>

    suspend fun resetCache()
}