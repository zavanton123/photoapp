package com.zavanton.photoapp.app.di

import android.content.Context
import androidx.room.Room
import com.zavanton.photoapp.photos.data.db.PhotoDao
import com.zavanton.photoapp.photos.data.db.PhotoDb
import dagger.Module
import dagger.Provides

interface DbDependencies {

    fun providePhotoDao(): PhotoDao
}

@Module
class DbModule {

    companion object {
        const val PHOTO_DB_NAME = "photo-db"
    }

    @AppScope
    @Provides
    fun providePhotoDb(context: Context): PhotoDb {
        return Room.databaseBuilder(context, PhotoDb::class.java, PHOTO_DB_NAME)
            .build()
    }

    @AppScope
    @Provides
    fun providePhotoDao(photoDb: PhotoDb): PhotoDao {
        return photoDb.photoDao()
    }
}
