package com.zavanton.photoapp.photos.di

import com.zavanton.photoapp.app.di.AppComponentManager
import com.zavanton.photoapp.app.di.FeatureScope
import com.zavanton.photoapp.app.di.NetworkDependencies
import com.zavanton.photoapp.photos.ui.PhotosActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        NetworkDependencies::class,
    ],
    modules = [
        ApiModule::class,
    ]
)
interface PhotosComponent {

    fun inject(activity: PhotosActivity)
}

object PhotosComponentManager {

    private var photosComponent: PhotosComponent? = null

    val component: PhotosComponent
        get() {
            return photosComponent ?: DaggerPhotosComponent.builder()
                .networkDependencies(AppComponentManager.component)
                .build()
                .also {
                    photosComponent = it
                }
        }

    fun clear() {
        photosComponent = null
    }
}
