package com.zavanton.photoapp.app

import android.app.Application
import android.util.Log
import com.zavanton.photoapp.app.di.AppComponentManager

class PhotoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("zavanton", "zavanton - photo app")

        AppComponentManager.init(context = this)
    }
}
