package com.zavanton.photoapp.app.di

import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkingModule {

    companion object {
        const val BASE_URL = "https://marlove.net/e/mock/"
    }

    @AppScope
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("zavanton", "zavanton - $message")
        }
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @AppScope
    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @AppScope
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    @AppScope
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }
}
