package com.zavanton.photoapp.photos.data.repository

import com.zavanton.photoapp.app.di.API_KEY_VALUE
import com.zavanton.photoapp.photos.business.IPhotosRepository
import com.zavanton.photoapp.photos.business.models.PhotoBusinessModel
import com.zavanton.photoapp.photos.data.api.PhotoApi
import com.zavanton.photoapp.photos.data.api.models.toBusinessModel
import javax.inject.Inject
import javax.inject.Named

class PhotosRepository @Inject constructor(
    private val photoApi: PhotoApi,
    @Named(API_KEY_VALUE)
    private val apiKey: String,
) : IPhotosRepository {

    override suspend fun downloadPhotos(): List<PhotoBusinessModel> {
        return photoApi
            .download(apiKey)
            .map {
                it.toBusinessModel()
            }
    }
}
