package com.zavanton.photoapp.app.di

import retrofit2.Retrofit
import javax.inject.Named

interface NetworkDependencies {

    fun provideRetrofit(): Retrofit

    @Named(API_KEY_VALUE)
    fun provideApiKey(): String
}