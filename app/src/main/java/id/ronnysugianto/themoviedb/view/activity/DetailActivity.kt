package id.ronnysugianto.themoviedb.view.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.ronnysugianto.themoviedb.R
import id.ronnysugianto.themoviedb.data.Movie
import id.ronnysugianto.themoviedb.data.Result
import id.ronnysugianto.themoviedb.data.Trailer
import id.ronnysugianto.themoviedb.databinding.ActivityDetailBinding
import id.ronnysugianto.themoviedb.util.GridSpacingItemDecoration
import id.ronnysugianto.themoviedb.util.ItemClickListener
import id.ronnysugianto.themoviedb.util.loadUrl
import id.ronnysugianto.themoviedb.util.viewBinding
import id.ronnysugianto.themoviedb.view.adapter.ReviewAdapter
import id.ronnysugianto.themoviedb.view.adapter.TrailerAdapter

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class DetailActivity : BaseActivity() {
    private val binding by viewBinding(ActivityDetailBinding::inflate)

    companion object {
        const val EXT_MOVIE = "EXT_MOVIE_ID"

        fun launchIntent(
            context: Context,
            movie: Movie,
        ) = Intent(context, DetailActivity::class.java).apply {
            putExtra(EXT_MOVIE, movie)
        }
    }


    private val movie by lazy { intent.getParcelableExtra(EXT_MOVIE, Movie::class.java ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBasicInformation()
        setupTrailer()
        setupReview()
    }

    private fun setupBasicInformation() = binding.apply {
        ivBack.setOnClickListener { finish() }
        movie?.backdrop_path?.let { ivPoster.loadUrl(it) }
        tvTitle.text = movie?.title
        tvOverview.text = movie?.overview
    }

    private fun setupTrailer() {
        val trailerAdapter = TrailerAdapter(object: ItemClickListener<Trailer> {
            override fun onItemClick(data: Trailer) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.youtube_url, data.key)))
                startActivity(intent)
            }
        })

        binding.rvTrailer.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GridSpacingItemDecoration(2, 16, true))
            adapter = trailerAdapter
        }

        movieViewModel.trailers.observe(this) {
            when (it) {
                is Result.Success ->  it.data?.results?.let { trailers ->
                    trailerAdapter.setData(trailers)
                    if(trailers.isNotEmpty()) {
                        binding.tvTrailer.visibility = View.VISIBLE
                    }
                }
                is Result.Error -> Snackbar.make(
                    binding.root,
                    resources.getString(R.string.error_message, it.exception.message),
                    Snackbar.LENGTH_SHORT
                ).show()

                else -> {}
            }
        }

        movie?.id?.let { movieViewModel.getMovieTrailer(it) }
    }

    private fun setupReview() {
        val reviewAdapter = ReviewAdapter()

        binding.rvReview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = reviewAdapter
        }

        movieViewModel.reviews.observe(this) {
            when (it) {
                is Result.Success -> it.data?.results?.let { reviews ->
                    reviewAdapter.setData(reviews)
                    if(reviews.isNotEmpty()) {
                        binding.tvReview.visibility = View.VISIBLE
                    }
                }
                is Result.Error -> Snackbar.make(
                    binding.root,
                    resources.getString(R.string.error_message, it.exception.message),
                    Snackbar.LENGTH_SHORT
                ).show()

                else -> {}
            }
        }

        movie?.id?.let { movieViewModel.getMovieReview(it) }
    }
}