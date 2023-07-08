package id.ronnysugianto.themoviedb.network.api

import id.ronnysugianto.themoviedb.data.GenreResponse
import id.ronnysugianto.themoviedb.data.MovieItemResponse
import id.ronnysugianto.themoviedb.data.MovieResponse
import id.ronnysugianto.themoviedb.data.MovieReviewsResponse
import id.ronnysugianto.themoviedb.data.MovieTrailersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("with_genres") genreId: Int
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): Response<MovieItemResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int
    ): Response<MovieReviewsResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: Int
    ): Response<MovieTrailersResponse>

}
