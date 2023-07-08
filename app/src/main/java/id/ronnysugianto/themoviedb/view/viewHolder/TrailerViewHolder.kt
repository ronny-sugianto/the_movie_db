package id.ronnysugianto.themoviedb.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.R
import id.ronnysugianto.themoviedb.data.Trailer
import id.ronnysugianto.themoviedb.databinding.TrailerItemBinding
import id.ronnysugianto.themoviedb.util.ItemClickListener
import id.ronnysugianto.themoviedb.util.loadUrl

class TrailerViewHolder(
    private val binding: TrailerItemBinding,
    private val onClickItem: ItemClickListener<Trailer>,
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(trailer: Trailer) = binding.root.apply {
        loadUrl(resources.getString(R.string.youtube_thumbnail, trailer.key))
        setOnClickListener{
            onClickItem.onItemClick(trailer)
        }
    }
}