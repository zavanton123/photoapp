package com.zavanton.photoapp.photos.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.zavanton.photoapp.R
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

        if (photos.loadState.append == LoadState.Loading) {
            item {
                LoadingData()
            }
        }

        when (photos.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    LoadingData()
                }
            }
            is LoadState.Error -> {
                item {
                    LoadingDataError()
                }
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
private fun LoadingData() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = stringResource(R.string.loading),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun LoadingDataError() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = stringResource(R.string.load_error),
        textAlign = TextAlign.Center,
    )
}

// todo zavanton - implement
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoAppTheme {
    }
}
