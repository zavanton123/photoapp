package com.zavanton.photoapp.photos.data.repository

import android.util.Log
import com.zavanton.photoapp.app.di.API_KEY_VALUE
import com.zavanton.photoapp.photos.business.IPhotosRepository
import com.zavanton.photoapp.photos.business.PhotoBusinessModel
import com.zavanton.photoapp.photos.data.api.PhotoApi
import com.zavanton.photoapp.photos.data.api.toDbModel
import com.zavanton.photoapp.photos.data.db.PhotoDao
import com.zavanton.photoapp.photos.data.db.PhotoDbModel
import com.zavanton.photoapp.photos.data.db.toBusinessModel
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Named

class PhotosRepository @Inject constructor(
    @Named(API_KEY_VALUE)
    private val apiKey: String,
    private val photoApi: PhotoApi,
    private val photoDao: PhotoDao,
) : IPhotosRepository {

    override suspend fun downloadPhotos(maxPhotoId: String?): List<PhotoBusinessModel> {
        val models: List<PhotoDbModel> = photoApi
            .download(
                apiKey = apiKey,
                maxPhotoId = maxPhotoId,
            )
            .map {
                it.toDbModel()
            }
            .onEach {
                // todo zavanton - delete
                delay(100)
            }

        // todo zavanton - delete
        Log.d("zavanton", "zavanton - before insert")
        photoDao.insertPhotos(models)

        return models.map {
            it.toBusinessModel()
        }
    }
}
