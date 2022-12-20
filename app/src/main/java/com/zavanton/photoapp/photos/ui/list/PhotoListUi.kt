package com.zavanton.photoapp.photos.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zavanton.photoapp.photos.ui.models.PhotoListUiState
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel
import com.zavanton.photoapp.ui.theme.PhotoAppTheme


@Composable
fun PhotoListScreen(
    state: PhotoListUiState,
) {
    when (state) {
        PhotoListUiState.Loading -> {
            LoadingPhotoList()
        }
        PhotoListUiState.Error -> {
            LoadingPhotoListError()
        }
        is PhotoListUiState.Loaded -> {
            LoadedPhotos(state.photos)
        }
    }
}

@Composable
private fun LoadedPhotos(
    photos: List<PhotoUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(photos) { photo ->
            Text(
                text = photo.description,
            )
        }
    }
}

@Composable
private fun LoadingPhotoList(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Loading..."
        )
    }
}

@Composable
private fun LoadingPhotoListError(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Failed to load photos"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoAppTheme {
        // PhotoList("Android")
    }
}
