package id.ronnysugianto.themoviedb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import id.ronnysugianto.themoviedb.di.DaggerAppComponent

@HiltAndroidApp
class MainApp : Application() {
    val appComponent = DaggerAppComponent.builder()
        .mainApp(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}