package id.ronnysugianto.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.data.Genre
import id.ronnysugianto.themoviedb.databinding.GenreItemBinding
import id.ronnysugianto.themoviedb.util.DiffCallback
import id.ronnysugianto.themoviedb.util.ItemClickListener
import id.ronnysugianto.themoviedb.view.viewHolder.GenreViewHolder

class GenreAdapter(private val onClickItem: ItemClickListener<Genre>) :
    RecyclerView.Adapter<GenreViewHolder>() {


    private val callback = DiffCallback<Genre>(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )

    private val genres = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItem
        )

    override fun getItemCount(): Int = genres.currentList.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres.currentList[position])
    }

    fun setData(genres: List<Genre>) {
        this.genres.submitList(genres)
    }

}