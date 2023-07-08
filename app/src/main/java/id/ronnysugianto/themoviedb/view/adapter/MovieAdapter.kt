package id.ronnysugianto.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.data.Movie
import id.ronnysugianto.themoviedb.databinding.MovieItemBinding
import id.ronnysugianto.themoviedb.util.DiffCallback
import id.ronnysugianto.themoviedb.util.ItemClickListener
import id.ronnysugianto.themoviedb.view.viewHolder.MovieViewHolder

class MovieAdapter(private val onClickItem: ItemClickListener<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {


    private val callback = DiffCallback<Movie>(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )

    private val movies = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItem
        )

    override fun getItemCount(): Int = movies.currentList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.currentList[position])
    }

    fun setData(movies: List<Movie>) {
        println("=======+> masok: $movies")
        this.movies.submitList(movies)
    }

}