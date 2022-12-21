package com.zavanton.photoapp.photos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.zavanton.photoapp.photos.di.PhotosComponentManager
import com.zavanton.photoapp.photos.navigation.PhotosNavHost
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel
import com.zavanton.photoapp.ui.theme.PhotoAppTheme
import javax.inject.Inject

class PhotosActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: PhotosViewModelFactory

    private val photosViewModel by viewModels<PhotosViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        PhotosComponentManager.component.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            val items: LazyPagingItems<PhotoUiModel> =
                photosViewModel.pager.collectAsLazyPagingItems()

            PhotoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    PhotosNavHost(
                        items = items,
                    )
                }
            }
        }
    }
}
