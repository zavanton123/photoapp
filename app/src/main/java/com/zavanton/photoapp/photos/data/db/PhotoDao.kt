package com.zavanton.photoapp.photos.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {

    @Query("select * from photo")
    suspend fun fetchAllPhotos(): List<PhotoDbModel>

    @Query("select * from photo order by id asc limit 1")
    suspend fun fetchLastPhoto(): List<PhotoDbModel>

    @Query("select * from photo order by id desc limit 1")
    suspend fun fetchFirstPhoto(): List<PhotoDbModel>

    @Query("select * from photo where id=:id limit 1")
    suspend fun fetchPhotoById(id: String): PhotoDbModel

    @Query("select * from photo where id > :id limit 1")
    suspend fun fetchPreviousPhoto(id: String): PhotoDbModel

    @Query("select * from photo order by id desc limit :pageSize")
    suspend fun fetchFirstPage(pageSize: Int): List<PhotoDbModel>

    @Query("select * from photo where id < :maxId order by id desc limit :pageSize")
    suspend fun fetchPhotoPage(maxId: String, pageSize: Int): List<PhotoDbModel>

    @Insert
    suspend fun insertPhotos(photos: List<PhotoDbModel>)

    @Query("delete from photo")
    suspend fun removeAll()
}
