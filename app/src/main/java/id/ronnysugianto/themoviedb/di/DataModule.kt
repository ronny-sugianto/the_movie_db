package id.ronnysugianto.themoviedb.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import id.ronnysugianto.themoviedb.network.repository.BaseMovieRepository
import id.ronnysugianto.themoviedb.network.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module(includes = [DataModule.Bindings::class])
class DataModule {

    companion object {
        const val DEP_NETWORK_DISPATCHER = "DEP_NETWORK_DISPATCHER"
    }

    @Provides
    @Named(DEP_NETWORK_DISPATCHER)
    fun ioDispatcher() = Dispatchers.IO

    @Module
    interface Bindings {
        @Binds
        fun provideMovieRepository(mediaCloudRepository: MovieRepository): BaseMovieRepository
    }
}