package com.zavanton.photoapp.photos.di

import com.zavanton.photoapp.app.di.FeatureScope
import com.zavanton.photoapp.photos.business.IPhotosInteractor
import com.zavanton.photoapp.photos.business.IPhotosRepository
import com.zavanton.photoapp.photos.business.PhotosInteractor
import com.zavanton.photoapp.photos.data.api.PhotoApi
import com.zavanton.photoapp.photos.data.repository.PhotosRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface PhotosInteractorModule {

    @FeatureScope
    @Binds
    fun bindsInteractor(impl: PhotosInteractor): IPhotosInteractor
}

@Module
interface PhotosRepositoryModule {

    @FeatureScope
    @Binds
    fun bindsRepository(impl: PhotosRepository): IPhotosRepository
}

@Module
class ApiModule {

    @FeatureScope
    @Provides
    fun providePhotoApi(retrofit: Retrofit): PhotoApi {
        return retrofit.create(PhotoApi::class.java)
    }
}
