package id.ronnysugianto.themoviedb.di

import android.app.Application
import dagger.Module
import dagger.Provides
import id.ronnysugianto.themoviedb.MainApp

@Module(includes = [AppModule.Bindings::class])
class AppModule {
    @Module
    interface Bindings

    @Provides
    fun application(mainApp: MainApp): Application = mainApp
}