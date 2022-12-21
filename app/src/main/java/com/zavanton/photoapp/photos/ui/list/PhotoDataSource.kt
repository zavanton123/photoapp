package com.zavanton.photoapp.photos.ui.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zavanton.photoapp.EMPTY
import com.zavanton.photoapp.photos.business.IPhotosInteractor
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel
import com.zavanton.photoapp.photos.ui.models.toUiModel

class PhotoDataSource(
    private val photoInteractor: IPhotosInteractor,
) : PagingSource<String, PhotoUiModel>() {

    override fun getRefreshKey(state: PagingState<String, PhotoUiModel>): String {
        return state.lastItemOrNull()?.photoId ?: EMPTY
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PhotoUiModel> {
        return try {
            val maxPhotoId = params.key

            val models = photoInteractor.downloadPhotos(maxPhotoId)
                .map {
                    it.toUiModel()
                }

            LoadResult.Page(
                data = models,
                // todo zavanton - check if prevKey = null is ok?
                prevKey = null,
                nextKey = models.last().photoId.takeIf { models.isNotEmpty() },
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
