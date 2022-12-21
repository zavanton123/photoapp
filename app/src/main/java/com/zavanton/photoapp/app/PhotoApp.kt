package com.zavanton.photoapp.app

import android.app.Application
import com.zavanton.photoapp.BuildConfig
import com.zavanton.photoapp.app.di.AppComponentManager

class PhotoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponentManager.init(
            context = this,
            apiKey = BuildConfig.API_KEY,
        )
    }
}
