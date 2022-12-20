package com.zavanton.photoapp.photos.di

import com.zavanton.photoapp.app.di.FeatureScope
import com.zavanton.photoapp.photos.data.MockResponse
import dagger.Module
import dagger.Provides


@Module
class ApiModule {

    @FeatureScope
    @Provides
    fun provideResponse(): MockResponse {
        return MockResponse(content = "hello world")
    }
}
