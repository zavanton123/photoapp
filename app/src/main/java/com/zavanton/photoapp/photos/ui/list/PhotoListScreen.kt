package com.zavanton.photoapp.photos.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel
import com.zavanton.photoapp.ui.theme.PhotoAppTheme


@Composable
fun PhotoListScreen(
    items: LazyPagingItems<PhotoUiModel>,
    onPhotoClicked: (PhotoUiModel) -> Unit,
) {
    LoadedPhotos(
        photos = items,
        onPhotoClicked = onPhotoClicked,
    )

//    when (state) {
//        PhotoListUiState.Loading -> {
//            LoadingPhotoList()
//        }
//        PhotoListUiState.Error -> {
//            LoadingPhotoListError()
//        }
//        is PhotoListUiState.Loaded -> {
//            LoadedPhotos(
//                photos = state.photos,
//                onPhotoClicked = onPhotoClicked,
//            )
//        }
//    }
}

@Composable
private fun LoadedPhotos(
    photos: LazyPagingItems<PhotoUiModel>,
    modifier: Modifier = Modifier,
    onPhotoClicked: (PhotoUiModel) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(photos) { item: PhotoUiModel? ->
            item?.let { photoUiModel ->
                PhotoListItem(
                    model = photoUiModel,
                    onPhotoClicked = onPhotoClicked,
                )
            }
        }

        when (photos.loadState.append) {
            is LoadState.Error -> {
                // todo zavanton - implement
            }
            LoadState.Loading -> {
                // todo zavanton - implement
            }
            is LoadState.NotLoading -> {
                // todo zavanton - implement
            }
        }

        when (photos.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    // todo zavanton - implement
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        CircularProgressIndicator()
//                    }
                }
            }
            is LoadState.Error -> {
                // todo zavanton - implement
            }
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
            .fillMaxWidth()
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
