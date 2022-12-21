package com.zavanton.photoapp.photos.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zavanton.photoapp.photos.ui.models.PhotoListUiState
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel
import com.zavanton.photoapp.ui.theme.PhotoAppTheme


@Composable
fun PhotoListScreen(
    state: PhotoListUiState,
    onPhotoClicked: (PhotoUiModel) -> Unit,
) {
    when (state) {
        PhotoListUiState.Loading -> {
            LoadingPhotoList()
        }
        PhotoListUiState.Error -> {
            LoadingPhotoListError()
        }
        is PhotoListUiState.Loaded -> {
            LoadedPhotos(
                photos = state.photos,
                onPhotoClicked = onPhotoClicked,
            )
        }
    }
}

@Composable
private fun LoadedPhotos(
    photos: List<PhotoUiModel>,
    modifier: Modifier = Modifier,
    onPhotoClicked: (PhotoUiModel) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(photos) { photo ->
            PhotoListItem(
                model = photo,
                onPhotoClicked = onPhotoClicked,
            )
        }
    }
}

@Composable
private fun PhotoListItem(
    model: PhotoUiModel,
    modifier: Modifier = Modifier,
    onPhotoClicked: (PhotoUiModel) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable {
                onPhotoClicked(model)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(96.dp),
            model = model.imageLink,
            contentDescription = null,
        )

        Column {
            Text(text = model.description)
            Text(text = model.photoId)
            Text(text = model.confidence.toString())
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
