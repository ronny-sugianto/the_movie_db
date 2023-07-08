package id.ronnysugianto.themoviedb.di

import dagger.BindsInstance
import dagger.Component
import id.ronnysugianto.themoviedb.MainApp
import id.ronnysugianto.themoviedb.view.activity.BaseActivity
import id.ronnysugianto.themoviedb.di.viewModel.ViewModelFactoryModule
import id.ronnysugianto.themoviedb.di.viewModel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class,
    DataModule::class,
    NetworkModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun mainApp(application: MainApp): Builder
    }

    fun inject(mainApp: MainApp)
    fun inject(baseActivity: BaseActivity)
}
