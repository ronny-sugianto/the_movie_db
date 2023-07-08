package id.ronnysugianto.themoviedb.network.repository

import id.ronnysugianto.themoviedb.data.GenreResponse
import id.ronnysugianto.themoviedb.data.MovieItemResponse
import id.ronnysugianto.themoviedb.data.MovieResponse
import id.ronnysugianto.themoviedb.data.MovieReviewsResponse
import id.ronnysugianto.themoviedb.data.MovieTrailersResponse
import id.ronnysugianto.themoviedb.data.Result
import id.ronnysugianto.themoviedb.di.DataModule
import id.ronnysugianto.themoviedb.network.api.MovieApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    @Named(DataModule.DEP_NETWORK_DISPATCHER)
    private val networkDispatcher: CoroutineDispatcher,
) : BaseMovieRepository,
    BaseRepository() {

    override suspend fun getMovieGenre(): Flow<Result<GenreResponse?>> = flowData {
        movieApi.getGenres().body()
    }.flowOn(networkDispatcher)

    override suspend fun getMovieByGenre(genreId: Int): Flow<Result<MovieResponse?>> = flowData {
        movieApi.getMovieByGenre(genreId).body()
    }.flowOn(networkDispatcher)

    override suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieItemResponse?>> = flowData {
        movieApi.getMovieDetail(movieId).body()
    }.flowOn(networkDispatcher)

    override suspend fun getMovieTrailer(movieId: Int): Flow<Result<MovieTrailersResponse?>> = flowData {
        movieApi.getMovieTrailers(movieId).body()
    }.flowOn(networkDispatcher)

    override suspend fun getMovieReview(movieId: Int): Flow<Result<MovieReviewsResponse?>> = flowData {
        movieApi.getMovieReviews(movieId).body()
    }.flowOn(networkDispatcher)

}
