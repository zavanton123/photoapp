package com.zavanton.photoapp.photos.business

interface IPhotosRepository {

    suspend fun downloadPhotos(maxPhotoId: String?): List<PhotoBusinessModel>
}
