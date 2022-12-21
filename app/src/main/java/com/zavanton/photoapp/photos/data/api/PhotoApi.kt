package com.zavanton.photoapp.photos.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PhotoApi {

    @GET("v1/items")
    suspend fun download(
        @Header("Authorization") apiKey: String,
        @Query("max_id") maxPhotoId: String? = null,
    ): List<PhotoApiModel>
}
