package id.ronnysugianto.themoviedb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import id.ronnysugianto.themoviedb.BuildConfig
import id.ronnysugianto.themoviedb.network.api.MovieApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                addHeader("Authorization", "Bearer ${BuildConfig.API_ACCESS_TOKEN}")
            }.build())
        }
        return builder
    }

    @Provides
    fun provideRetrofit(httpClientBuilder: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()
    }

    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}
