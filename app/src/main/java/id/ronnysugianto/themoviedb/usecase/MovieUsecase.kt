package id.ronnysugianto.themoviedb.usecase

import id.ronnysugianto.themoviedb.data.GenreResponse
import id.ronnysugianto.themoviedb.data.MovieItemResponse
import id.ronnysugianto.themoviedb.data.MovieResponse
import id.ronnysugianto.themoviedb.data.MovieReviewsResponse
import id.ronnysugianto.themoviedb.data.MovieTrailersResponse
import id.ronnysugianto.themoviedb.data.Result
import id.ronnysugianto.themoviedb.network.repository.BaseMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MovieUsecase @Inject constructor(
    private val moveRepository: BaseMovieRepository
) {
    suspend fun getMovieGenre(): Flow<Result<GenreResponse?>> = channelFlow {
        moveRepository.getMovieGenre().collectLatest {
            send(it)
        }
    }

    suspend fun getMovieByGenre(genreId: Int): Flow<Result<MovieResponse?>> = channelFlow {
        moveRepository.getMovieByGenre(genreId).collectLatest {
            send(it)
        }
    }

    suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieItemResponse?>> = channelFlow {
        moveRepository.getMovieDetail(movieId).collectLatest {
            send(it)
        }
    }

    suspend fun getMovieTrailer(movieId: Int): Flow<Result<MovieTrailersResponse?>> = channelFlow {
        moveRepository.getMovieTrailer(movieId).collectLatest {
            send(it)
        }
    }

    suspend fun getMovieReview(movieId: Int): Flow<Result<MovieReviewsResponse?>> = channelFlow {
        moveRepository.getMovieReview(movieId).collectLatest {
            send(it)
        }
    }


}