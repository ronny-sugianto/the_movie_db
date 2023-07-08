package id.ronnysugianto.themoviedb.network.repository

import id.ronnysugianto.themoviedb.data.GenreResponse
import id.ronnysugianto.themoviedb.data.MovieItemResponse
import id.ronnysugianto.themoviedb.data.MovieResponse
import id.ronnysugianto.themoviedb.data.MovieReviewsResponse
import id.ronnysugianto.themoviedb.data.MovieTrailersResponse
import id.ronnysugianto.themoviedb.data.Result
import kotlinx.coroutines.flow.Flow

interface BaseMovieRepository {
    suspend fun getMovieGenre(): Flow<Result<GenreResponse?>>
    suspend fun getMovieByGenre(genreId: Int): Flow<Result<MovieResponse?>>
    suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieItemResponse?>>
    suspend fun getMovieTrailer(movieId: Int): Flow<Result<MovieTrailersResponse?>>
    suspend fun getMovieReview(movieId: Int): Flow<Result<MovieReviewsResponse?>>
}