package com.zavanton.photoapp.photos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zavanton.photoapp.photos.business.IPhotosInteractor
import com.zavanton.photoapp.photos.di.PhotosComponentManager
import com.zavanton.photoapp.photos.ui.list.PhotoPagingSource
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosViewModel constructor(
    private val photosInteractor: IPhotosInteractor,
) : ViewModel() {

    val pager: Flow<PagingData<PhotoUiModel>> = Pager(
        PagingConfig(pageSize = 10)
    ) {
        PhotoPagingSource(photosInteractor)
    }
        .flow
        .cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        PhotosComponentManager.clear()
    }
}

// todo zavanton - implement DI via @BindsInto
class PhotosViewModelFactory @Inject constructor(
    private val photosInteractor: IPhotosInteractor,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotosViewModel(
            photosInteractor,
        ) as T
    }
}
