package com.zavanton.photoapp.app

import android.app.Application
import android.util.Log

class PhotoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("zavanton", "zavanton - photo app")
    }
}
