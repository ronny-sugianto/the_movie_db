package id.ronnysugianto.themoviedb.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.data.Genre
import id.ronnysugianto.themoviedb.databinding.GenreItemBinding
import id.ronnysugianto.themoviedb.util.ItemClickListener

class GenreViewHolder(
    private val binding: GenreItemBinding,
    private val onClickItem: ItemClickListener<Genre>,
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(genre: Genre) = binding.root.apply {
        text = genre.name
        setOnClickListener{
            onClickItem.onItemClick(genre)
        }
    }
}