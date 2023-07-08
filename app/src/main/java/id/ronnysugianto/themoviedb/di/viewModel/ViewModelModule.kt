package id.ronnysugianto.themoviedb.di.viewModel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import id.ronnysugianto.themoviedb.viewModel.MovieViewModel
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun movieViewModel(movieViewModel: MovieViewModel): ViewModel
}

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)