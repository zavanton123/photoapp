package com.zavanton.photoapp.photos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.zavanton.photoapp.photos.business.IPhotosInteractor
import com.zavanton.photoapp.photos.di.PhotosComponentManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotosViewModel constructor(
    private val photosInteractor: IPhotosInteractor,
) : ViewModel() {

    init {
        // todo zavanton - delete
        Log.d("zavanton", "zavanton - view model init")

        viewModelScope.launch {
            val photos = photosInteractor.downloadPhotos()

            photos.forEach {
                // todo zavanton - delete
                Log.d("zavanton", "zavanton - photo: $it")
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
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotosViewModel(
            photosInteractor,
        ) as T
    }
}
