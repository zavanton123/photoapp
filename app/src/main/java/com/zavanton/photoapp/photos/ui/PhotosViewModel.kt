package com.zavanton.photoapp.photos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zavanton.photoapp.photos.business.IPhotosInteractor
import com.zavanton.photoapp.photos.data.db.PhotoDao
import com.zavanton.photoapp.photos.di.PhotosComponentManager
import com.zavanton.photoapp.photos.ui.list.PhotoPagingSource
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosViewModel constructor(
    private val photosInteractor: IPhotosInteractor,

    // todo zavanton - delete
    private val photoDao: PhotoDao,

    ) : ViewModel() {

    val pager: Flow<PagingData<PhotoUiModel>> = Pager(
        PagingConfig(pageSize = 10)
    ) {
        PhotoPagingSource(photosInteractor)
    }
        .flow
        .cachedIn(viewModelScope)

    // todo zavanton - delete
    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // photoDao.removeAll()

//                val id = "6194df94e5d1e"
//                val photos = photoDao.fetchPhotoPage(id, 10)
//
//                photos.forEach {
//                    // todo zavanton - delete
//                    Log.d("zavanton", "zavanton - photo: $it")
//                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        PhotosComponentManager.clear()
    }
}

// todo zavanton - implement DI via @BindsInto
class PhotosViewModelFactory @Inject constructor(
    private val photosInteractor: IPhotosInteractor,

    // todo zavanton - delete
    private val photoDao: PhotoDao,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotosViewModel(
            photosInteractor,
            photoDao,
        ) as T
    }
}
