package com.zavanton.photoapp.app

import android.app.Application
import android.util.Log
import com.zavanton.photoapp.BuildConfig
import com.zavanton.photoapp.app.di.AppComponentManager

class PhotoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // todo zavanton - delete
        Log.d("zavanton", "zavanton - photo app key: ${BuildConfig.API_KEY}")

        AppComponentManager.init(
            context = this,
            apiKey = BuildConfig.API_KEY,
        )
    }
}
