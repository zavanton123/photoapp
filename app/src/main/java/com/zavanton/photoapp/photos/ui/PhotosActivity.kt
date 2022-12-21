package com.zavanton.photoapp.photos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zavanton.photoapp.photos.di.PhotosComponentManager
import com.zavanton.photoapp.photos.navigation.NavigationDestinations.PHOTO_DETAILS
import com.zavanton.photoapp.photos.navigation.NavigationDestinations.PHOTO_LIST
import com.zavanton.photoapp.photos.ui.details.PhotoDetailsScreen
import com.zavanton.photoapp.photos.ui.list.PhotoListScreen
import com.zavanton.photoapp.photos.ui.models.PhotoListUiState
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
            val state: PhotoListUiState by photosViewModel.state.collectAsState(initial = PhotoListUiState.Loading)

            PhotoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    PhotosNavHost(state = state)
                }
            }
        }
    }
}

@Composable
fun PhotosNavHost(
    state: PhotoListUiState,
    navController: NavHostController = rememberNavController(),
    startDestination: String = PHOTO_LIST,
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(PHOTO_LIST) {
            PhotoListScreen(state = state)
        }
        composable(PHOTO_DETAILS) {
            PhotoDetailsScreen()
        }
    }
}
