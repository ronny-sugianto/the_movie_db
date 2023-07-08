package id.ronnysugianto.themoviedb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ronnysugianto.themoviedb.data.GenreResponse
import id.ronnysugianto.themoviedb.data.MovieItemResponse
import id.ronnysugianto.themoviedb.data.MovieResponse
import id.ronnysugianto.themoviedb.data.MovieReviewsResponse
import id.ronnysugianto.themoviedb.data.MovieTrailersResponse
import id.ronnysugianto.themoviedb.data.Result
import id.ronnysugianto.themoviedb.usecase.MovieUsecase
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieUsecase: MovieUsecase
) : BaseViewModel() {
    private val _genres = MutableLiveData<Result<GenreResponse?>>()
    val genres: LiveData<Result<GenreResponse?>> get() = _genres

    private val _movies = MutableLiveData<Result<MovieResponse?>>()
    val movies: LiveData<Result<MovieResponse?>> get() = _movies

    private val _movie = MutableLiveData<Result<MovieItemResponse?>>()
    val movie: LiveData<Result<MovieItemResponse?>> get() = _movie


    private val _trailers = MutableLiveData<Result<MovieTrailersResponse?>>()
    val trailers: LiveData<Result<MovieTrailersResponse?>> get() = _trailers


    private val _reviews = MutableLiveData<Result<MovieReviewsResponse?>>()
    val reviews: LiveData<Result<MovieReviewsResponse?>> get() = _reviews


    init {
        default()
    }

    fun default() {
        getMovieGenre()
        getMovieByGenre(28)
    }

    fun getMovieGenre() = launch {
        movieUsecase.getMovieGenre().collectLatest {
            _genres.value = it
        }
    }

    fun getMovieByGenre(genreId: Int) = launch {
        movieUsecase.getMovieByGenre(genreId).collectLatest {
            _movies.value = it
        }
    }

    fun getMovieDetail(movieId: Int) = launch {
        movieUsecase.getMovieDetail(movieId).collectLatest {
            _movie.value = it
        }
    }

    fun getMovieTrailer(movieId: Int) = launch {
        movieUsecase.getMovieTrailer(movieId).collectLatest {
            _trailers.value = it
        }
    }

    fun getMovieReview(movieId: Int) = launch {
        movieUsecase.getMovieReview(movieId).collectLatest {
            _reviews.value = it
        }
    }
}
