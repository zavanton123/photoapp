package com.zavanton.photoapp.photos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zavanton.photoapp.photos.data.MockResponse
import javax.inject.Inject

class PhotosViewModel constructor(
    private val mockResponse: MockResponse,
) : ViewModel() {

    init {
        // todo zavanton - delete
        Log.d("zavanton", "zavanton - view model init")
    }
}

class PhotosViewModelFactory @Inject constructor(
    private val mockResponse: MockResponse,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotosViewModel(mockResponse) as T
    }
}
