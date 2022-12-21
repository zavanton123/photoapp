package com.zavanton.photoapp.photos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import com.zavanton.photoapp.EMPTY
import com.zavanton.photoapp.photos.navigation.NavigationArguments.CONFIDENCE
import com.zavanton.photoapp.photos.navigation.NavigationArguments.IMAGE_URL
import com.zavanton.photoapp.photos.navigation.NavigationArguments.PHOTO_ID
import com.zavanton.photoapp.photos.navigation.NavigationArguments.PHOTO_TITLE
import com.zavanton.photoapp.photos.ui.details.PhotoDetailsScreen
import com.zavanton.photoapp.photos.ui.list.PhotoListScreen
import com.zavanton.photoapp.photos.ui.models.PhotoUiModel

@Composable
fun PhotosNavHost(
    items: LazyPagingItems<PhotoUiModel>,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationDestinations.PHOTO_LIST,
    onSwipe: suspend () -> Unit,
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationDestinations.PHOTO_LIST) {
            val onPhotoClicked: (PhotoUiModel) -> Unit =
                { photoUiModel ->
                    navController.navigate(
                        route = NavigationDestinations.PHOTO_DETAILS +
                                "?${PHOTO_ID}=${photoUiModel.photoId}" +
                                "&${PHOTO_TITLE}=${photoUiModel.description}" +
                                "&${IMAGE_URL}=${photoUiModel.imageLink}" +
                                "&${CONFIDENCE}=${photoUiModel.confidence}"
                    )
                }

            PhotoListScreen(
                items = items,
                onPhotoClicked = onPhotoClicked,
                onSwipe = onSwipe,
            )
        }
        composable(
            route = NavigationDestinations.PHOTO_DETAILS +
                    "?${PHOTO_ID}={$PHOTO_ID}" +
                    "&${PHOTO_TITLE}={$PHOTO_TITLE}" +
                    "&${IMAGE_URL}={$IMAGE_URL}" +
                    "&${CONFIDENCE}={$CONFIDENCE}",
            arguments = listOf(
                navArgument(PHOTO_ID) { defaultValue = EMPTY },
                navArgument(PHOTO_TITLE) { defaultValue = EMPTY },
                navArgument(IMAGE_URL) { defaultValue = EMPTY },
                navArgument(CONFIDENCE) { defaultValue = EMPTY },
            )
        ) { backStackEntry ->
            PhotoDetailsScreen(
                model = PhotoUiModel(
                    photoId = backStackEntry.arguments?.getString(PHOTO_ID) ?: EMPTY,
                    imageLink = backStackEntry.arguments?.getString(IMAGE_URL) ?: EMPTY,
                    description = backStackEntry.arguments?.getString(PHOTO_TITLE) ?: EMPTY,
                    confidence = (backStackEntry.arguments?.getString(CONFIDENCE) ?: EMPTY)
                        .toDoubleOrNull() ?: 0.0,
                ),
            )
        }
    }
}
