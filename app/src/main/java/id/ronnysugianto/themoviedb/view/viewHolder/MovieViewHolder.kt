package id.ronnysugianto.themoviedb.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.data.Movie
import id.ronnysugianto.themoviedb.databinding.MovieItemBinding
import id.ronnysugianto.themoviedb.util.ItemClickListener
import id.ronnysugianto.themoviedb.util.loadUrl


class MovieViewHolder(
    private val binding: MovieItemBinding,
    private val onClickItem: ItemClickListener<Movie>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) = binding.apply {
        movie.poster_path?.let { ivPoster.loadUrl(it) }
        tvLabel.text = movie.title
        root.setOnClickListener { onClickItem.onItemClick(movie) }
    }
}