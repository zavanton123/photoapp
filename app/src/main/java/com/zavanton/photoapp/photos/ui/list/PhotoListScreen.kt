package com.zavanton.photoapp.photos.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PhotoListScreen(
    items: LazyPagingItems<PhotoUiModel>,
    onPhotoClicked: (PhotoUiModel) -> Unit,
) {
    LoadedPhotos(
        photos = items,
        onPhotoClicked = onPhotoClicked,
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LoadedPhotos(
    photos: LazyPagingItems<PhotoUiModel>,
    modifier: Modifier = Modifier,
    onPhotoClicked: (PhotoUiModel) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = scope.launch {
        refreshing = true

        // todo zavanton - delete
        delay(1000)

        refreshing = false
    }

    val pullRefreshState = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        modifier = modifier.pullRefresh(pullRefreshState),
        contentAlignment = Alignment.Center,
    ) {
        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter),
        )

        LazyColumn {
            if (!refreshing) {
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
            // todo zavanton - delete
            .size(600.dp)
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
