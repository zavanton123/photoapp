package com.zavanton.photoapp.photos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PhotoDbModel::class],
    version = 1,
)
abstract class PhotoDb : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}
