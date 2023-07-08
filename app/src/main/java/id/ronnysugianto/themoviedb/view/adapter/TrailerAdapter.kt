package id.ronnysugianto.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.data.Trailer
import id.ronnysugianto.themoviedb.databinding.TrailerItemBinding
import id.ronnysugianto.themoviedb.util.DiffCallback
import id.ronnysugianto.themoviedb.util.ItemClickListener
import id.ronnysugianto.themoviedb.view.viewHolder.TrailerViewHolder

class TrailerAdapter(private val onClickItem: ItemClickListener<Trailer>) : RecyclerView.Adapter<TrailerViewHolder>() {

    private val callback = DiffCallback<Trailer>(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )

    private val trailers = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder =
        TrailerViewHolder(
            TrailerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItem
        )

    override fun getItemCount(): Int = trailers.currentList.size

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(trailers.currentList[position])
    }

    fun setData(trailers: List<Trailer>) {
        this.trailers.submitList(trailers)
    }

}