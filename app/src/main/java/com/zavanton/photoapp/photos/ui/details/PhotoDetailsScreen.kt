package com.zavanton.photoapp.photos.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel

@Composable
fun PhotoDetailsScreen(
    model: PhotoUiModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = model.imageLink,
                contentDescription = null,
            )
            Text(text = model.photoId)
            Text(text = model.description)
            Text(text = model.confidence.toString())
        }
    }
}
