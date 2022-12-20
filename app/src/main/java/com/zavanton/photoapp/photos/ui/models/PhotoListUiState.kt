package com.zavanton.photoapp.photos.ui.models

sealed interface PhotoListUiState {

    object Loading : PhotoListUiState

    object Error : PhotoListUiState

    data class Loaded(
        val photos: List<PhotoUiModel>,
    ) : PhotoListUiState
}
