package com.zavanton.photoapp.photos.business

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


internal class PhotosInteractorTest {

    private val photosRepository = mock<IPhotosRepository>()
    private val photosInteractor = PhotosInteractor(photosRepository)

    @Test
    fun downloadPhotos() {
        return runBlocking {
            // mock
            val maxPhotoId = "123"
            val expectedItems = listOf(
                PhotoBusinessModel(
                    id = "100",
                    imageUrl = "https://demo.com",
                    title = "some title",
                    confidence = 1.0,
                ),
            )
            whenever(photosRepository.downloadPhotos(maxPhotoId)).thenReturn(expectedItems)

            // action
            val actualItems = photosInteractor.downloadPhotos(maxPhotoId)

            // verify
            assert(actualItems == expectedItems)
            verify(photosRepository, times(1)).downloadPhotos(maxPhotoId)
        }
    }

    @Test
    fun resetCache() {
        runBlocking {
            // mock
            whenever(photosRepository.resetCache()).thenReturn(Unit)

            // action
            photosInteractor.resetCache()

            // verify
            verify(photosRepository, times(1)).resetCache()
        }
    }
}