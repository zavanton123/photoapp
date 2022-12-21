package com.zavanton.photoapp.photos.data.api

import com.zavanton.photoapp.photos.data.api.models.PhotoApiModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PhotoApi {

    @GET("v1/items")
    suspend fun download(
        @Header("Authorization")
        authorization: String,
    ): List<PhotoApiModel>

    @GET("v1/items")
    suspend fun downloadAfterId(
        @Header("Authorization") authorization: String,
        @Query("max_id") maxPhotoId: String,
    ): List<PhotoApiModel>
}