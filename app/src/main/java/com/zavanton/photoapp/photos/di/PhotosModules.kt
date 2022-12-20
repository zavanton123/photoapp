package com.zavanton.photoapp.photos.di

import com.zavanton.photoapp.app.di.FeatureScope
import com.zavanton.photoapp.photos.business.IPhotosInteractor
import com.zavanton.photoapp.photos.business.PhotosInteractor
import com.zavanton.photoapp.photos.data.MockResponse
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface PhotosInteractorModule {

    @FeatureScope
    @Binds
    fun bindsInteractor(impl: PhotosInteractor): IPhotosInteractor
}

@Module
class ApiModule {

    @FeatureScope
    @Provides
    fun provideResponse(): MockResponse {
        return MockResponse(content = "hello world")
    }
}
