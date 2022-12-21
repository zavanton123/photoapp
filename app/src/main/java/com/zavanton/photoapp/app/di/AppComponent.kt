package com.zavanton.photoapp.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

const val API_KEY_VALUE = "API_KEY_VALUE"

@AppScope
@Component(
    modules = [
        NetworkingModule::class,
        DbModule::class,
    ]
)
interface AppComponent : NetworkDependencies, DbDependencies {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun addContext(context: Context): Builder

        @BindsInstance
        fun apiKey(
            @Named(API_KEY_VALUE)
            apiKey: String,
        ): Builder

        fun build(): AppComponent
    }
}

object AppComponentManager {

    private lateinit var appComponent: AppComponent

    fun init(
        context: Context,
        apiKey: String,
    ) {
        if (!this::appComponent.isInitialized) {
            appComponent = DaggerAppComponent.builder()
                .addContext(context)
                .apiKey(apiKey)
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