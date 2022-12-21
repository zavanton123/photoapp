package com.zavanton.photoapp.photos.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {

    @Query("select * from photo")
    fun fetchPhotos(): List<PhotoDbModel>

    @Insert
    fun insertPhotos(vararg photos: PhotoDbModel)

    @Query("delete from photo")
    fun removeAll()
}
