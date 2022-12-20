package com.zavanton.photoapp.app.di

import retrofit2.Retrofit

interface NetworkDependencies {

    fun provideRetrofit(): Retrofit
}