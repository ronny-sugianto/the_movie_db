package id.ronnysugianto.themoviedb.view.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.ronnysugianto.themoviedb.R
import id.ronnysugianto.themoviedb.data.Genre
import id.ronnysugianto.themoviedb.data.Movie
import id.ronnysugianto.themoviedb.data.Result
import id.ronnysugianto.themoviedb.databinding.ActivityMainBinding
import id.ronnysugianto.themoviedb.util.GridSpacingItemDecoration
import id.ronnysugianto.themoviedb.util.ItemClickListener
import id.ronnysugianto.themoviedb.util.viewBinding
import id.ronnysugianto.themoviedb.view.adapter.GenreAdapter
import id.ronnysugianto.themoviedb.view.adapter.MovieAdapter

class MainActivity : BaseActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.refreshLayout.setOnRefreshListener {
            movieViewModel.default()
        }

        setupGenre()
        setupMovies()
    }

    private fun setupGenre() {
        val genreAdapter = GenreAdapter(object : ItemClickListener<Genre> {
            override fun onItemClick(data: Genre) {
                movieViewModel.getMovieByGenre(data.id)
            }
        })

        binding.rvGenre.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = genreAdapter
        }

        movieViewModel.genres.observe(this) {
            binding.refreshLayout.isRefreshing = it is Result.Loading
            when (it) {
                is Result.Success -> it.data?.genres?.let { genres -> genreAdapter.setData(genres) }
                is Result.Error -> Snackbar.make(
                    binding.root,
                    resources.getString(R.string.error_message, it.exception.message),
                    Snackbar.LENGTH_SHORT
                ).show()

                else -> {}
            }
        }
    }

    private fun setupMovies() {
        val movieAdapter = MovieAdapter(object : ItemClickListener<Movie> {
            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onItemClick(data: Movie) {
                startActivity(DetailActivity.launchIntent(applicationContext, data))
            }
        })

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GridSpacingItemDecoration(2, 16, true))
            adapter = movieAdapter
        }

        movieViewModel.movies.observe(this) {
            binding.refreshLayout.isRefreshing = it is Result.Loading
            when (it) {
                is Result.Success -> it.data?.results?.let { movies -> movieAdapter.setData(movies) }
                is Result.Error -> Snackbar.make(
                    binding.root,
                    resources.getString(R.string.error_message, it.exception.message),
                    Snackbar.LENGTH_SHORT
                ).show()

                else -> {}
            }
        }
    }
}