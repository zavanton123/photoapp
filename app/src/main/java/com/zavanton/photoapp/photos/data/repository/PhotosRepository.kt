package com.zavanton.photoapp.photos.data.repository

import com.zavanton.photoapp.app.di.API_KEY_VALUE
import com.zavanton.photoapp.photos.business.IPhotosRepository
import com.zavanton.photoapp.photos.business.PhotoBusinessModel
import com.zavanton.photoapp.photos.data.api.PhotoApi
import com.zavanton.photoapp.photos.data.api.toDbModel
import com.zavanton.photoapp.photos.data.db.PhotoDao
import com.zavanton.photoapp.photos.data.db.PhotoDbModel
import com.zavanton.photoapp.photos.data.db.toBusinessModel
import javax.inject.Inject
import javax.inject.Named

class PhotosRepository @Inject constructor(
    @Named(API_KEY_VALUE)
    private val apiKey: String,
    private val photoApi: PhotoApi,
    private val photoDao: PhotoDao,
) : IPhotosRepository {

    companion object {
        const val PAGE_SIZE = 10
    }

    override suspend fun downloadPhotos(maxPhotoId: String?): List<PhotoBusinessModel> {
        var models: List<PhotoDbModel> = fetchNext(maxPhotoId)

        if (models.isEmpty()) {
            models = photoApi
                .download(
                    apiKey = apiKey,
                    maxPhotoId = maxPhotoId,
                )
                .map {
                    it.toDbModel()
                }

            photoDao.insertPhotos(models)
        }

        return models.map {
            it.toBusinessModel()
        }
    }

    override suspend fun resetCache() {
        photoDao.removeAll()
    }

    private suspend fun fetchNext(maxPhotoId: String?): List<PhotoDbModel> {
        return if (maxPhotoId == null) {
            photoDao.fetchFirstPage(PAGE_SIZE)
        } else {
            photoDao.fetchPhotoPage(maxPhotoId, PAGE_SIZE)
        }
    }
}
