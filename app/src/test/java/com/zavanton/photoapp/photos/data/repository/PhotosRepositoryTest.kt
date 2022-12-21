package com.zavanton.photoapp.photos.data.repository

import com.zavanton.photoapp.photos.business.PhotoBusinessModel
import com.zavanton.photoapp.photos.data.api.PhotoApi
import com.zavanton.photoapp.photos.data.api.PhotoApiModel
import com.zavanton.photoapp.photos.data.db.PhotoDao
import com.zavanton.photoapp.photos.data.db.PhotoDbModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


internal class PhotosRepositoryTest {

    private val apiKey = "123-456"
    private val photoApi = mock<PhotoApi>()
    private val photoDao = mock<PhotoDao>()

    private val photosRepository = PhotosRepository(
        apiKey = apiKey,
        photoApi = photoApi,
        photoDao = photoDao,
    )

    @Test
    fun `test downloadPhotos when items are fetched from DB`() {
        return runBlocking {
            // mock
            val maxPhotoId = "123"
            val pageSize = 10
            val items = listOf(
                PhotoDbModel(
                    id = "100",
                    imageUrl = "https://demo.com",
                    title = "some title",
                    confidence = 1.0,
                ),
            )
            val expectedItems = listOf(
                PhotoBusinessModel(
                    id = "100",
                    imageUrl = "https://demo.com",
                    title = "some title",
                    confidence = 1.0,
                ),
            )
            whenever(photoDao.fetchPhotoPage(maxPhotoId, pageSize)).thenReturn(items)

            // action
            val actualItems = photosRepository.downloadPhotos(maxPhotoId)

            // verify
            verify(photoDao, times(1)).fetchPhotoPage(maxPhotoId, pageSize)
            verify(photoApi, times(0)).download(apiKey, maxPhotoId)
            assertEquals(expectedItems, actualItems)
        }
    }

    @Test
    fun `test downloadPhotos when items are fetched from network`() {
        return runBlocking {
            // mock
            val maxPhotoId = "123"
            val pageSize = 10

            val items = listOf(
                PhotoApiModel(
                    photoId = "100",
                    imageUrl = "https://demo.com",
                    description = "some title",
                    confidence = 1.0,
                ),
            )
            val expectedItems = listOf(
                PhotoBusinessModel(
                    id = "100",
                    imageUrl = "https://demo.com",
                    title = "some title",
                    confidence = 1.0,
                ),
            )
            whenever(photoDao.fetchPhotoPage(maxPhotoId, pageSize)).thenReturn(emptyList())
            whenever(photoApi.download(apiKey, maxPhotoId)).thenReturn(items)

            // action
            val actualItems = photosRepository.downloadPhotos(maxPhotoId)

            // verify
            verify(photoDao, times(1)).fetchPhotoPage(maxPhotoId, pageSize)
            verify(photoApi, times(1)).download(apiKey, maxPhotoId)
            assertEquals(expectedItems, actualItems)
        }
    }

    @Test
    fun `test resetCache`() {
        return runBlocking {
            // mock
            whenever(photoDao.removeAll()).thenReturn(Unit)

            // action
            photosRepository.resetCache()

            // verify
            verify(photoDao, times(1)).removeAll()
        }
    }

    @Test
    fun `test downloadPhotos when items are fetched from DB when maxPhotoId is null`() {
        return runBlocking {
            // mock
            val maxPhotoId = null
            val pageSize = 10
            val items = listOf(
                PhotoDbModel(
                    id = "100",
                    imageUrl = "https://demo.com",
                    title = "some title",
                    confidence = 1.0,
                ),
            )
            val expectedItems = listOf(
                PhotoBusinessModel(
                    id = "100",
                    imageUrl = "https://demo.com",
                    title = "some title",
                    confidence = 1.0,
                ),
            )
            whenever(photoDao.fetchFirstPage(pageSize)).thenReturn(items)

            // action
            val actualItems = photosRepository.downloadPhotos(maxPhotoId)

            // verify
            verify(photoDao, times(1)).fetchFirstPage(pageSize)
            verify(photoApi, times(0)).download(apiKey, maxPhotoId)
            assertEquals(expectedItems, actualItems)
        }
    }

    @Test
    fun `test downloadPhotos when items are fetched from network when maxPhotoId is null`() {
        return runBlocking {
            // mock
            val maxPhotoId = null
            val pageSize = 10

            val items = listOf(
                PhotoApiModel(
                    photoId = "100",
                    imageUrl = "https://demo.com",
                    description = "some title",
                    confidence = 1.0,
                ),
            )
            val expectedItems = listOf(
                PhotoBusinessModel(
                    id = "100",
                    imageUrl = "https://demo.com",
                    title = "some title",
                    confidence = 1.0,
                ),
            )
            whenever(photoDao.fetchFirstPage(pageSize)).thenReturn(emptyList())
            whenever(photoApi.download(apiKey, maxPhotoId)).thenReturn(items)

            // action
            val actualItems = photosRepository.downloadPhotos(maxPhotoId)

            // verify
            verify(photoDao, times(1)).fetchFirstPage(pageSize)
            verify(photoApi, times(1)).download(apiKey, maxPhotoId)
            assertEquals(expectedItems, actualItems)
        }
    }
}
