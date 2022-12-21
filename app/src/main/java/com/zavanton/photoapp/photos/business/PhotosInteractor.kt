package com.zavanton.photoapp.photos.business

import com.zavanton.photoapp.photos.business.models.PhotoBusinessModel
import javax.inject.Inject

class PhotosInteractor @Inject constructor(
    private val photosRepository: IPhotosRepository,
) : IPhotosInteractor {

    override suspend fun downloadPhotos(maxPhotoId: String?): List<PhotoBusinessModel> {
        return photosRepository.downloadPhotos(maxPhotoId)
    }
}
