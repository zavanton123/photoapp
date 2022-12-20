package com.zavanton.photoapp.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkingModule::class,
    ]
)
interface AppComponent : NetworkDependencies {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun addContext(context: Context): Builder

        fun build(): AppComponent
    }
}

object AppComponentManager {

    private lateinit var appComponent: AppComponent

    fun init(context: Context) {
        if (!this::appComponent.isInitialized) {
            appComponent = DaggerAppComponent.builder()
                .addContext(context)
                .build()
        }
    }

    val component: AppComponent
        get() {
            require(this::appComponent.isInitialized) {
                "AppComponent must be initialized first!"
            }
            return appComponent
        }
}