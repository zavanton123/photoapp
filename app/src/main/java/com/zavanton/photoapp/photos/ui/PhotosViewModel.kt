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
import com.zavanton.photoapp.photos.data.db.PhotoDbModel
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

    init {
        // todo zavanton - delete
        Log.d("zavanton", "zavanton - view model init")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                photoDao.removeAll()

                photoDao.insertPhotos(
                    listOf(
                        PhotoDbModel("6194df956cec4", "30", "", 0.0),
                        PhotoDbModel("6194df94e5d1e", "21", "", 0.0),
                        PhotoDbModel("6194df94d8de1", "20", "", 0.0),
                    )
                )

                val photo1 = photoDao.fetchLastPhoto()
                Log.d("zavanton", "zavanton - photo: $photo1")

                photoDao.insertPhotos(
                    listOf(
                        PhotoDbModel("6194df94590a4", "11", "", 0.0),
                        PhotoDbModel("6194df944b8ec", "10", "", 0.0),
                        PhotoDbModel("6194df93c1ea2", "1", "", 0.0),
                    )
                )

                val photo2 = photoDao.fetchLastPhoto()
                Log.d("zavanton", "zavanton - photo: $photo2")
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
