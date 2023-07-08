package id.ronnysugianto.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.data.Review
import id.ronnysugianto.themoviedb.databinding.ReviewItemBinding
import id.ronnysugianto.themoviedb.util.DiffCallback
import id.ronnysugianto.themoviedb.view.viewHolder.ReviewViewHolder

class ReviewAdapter() : RecyclerView.Adapter<ReviewViewHolder>() {


    private val callback = DiffCallback<Review>(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )

    private val reviews = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder =
        ReviewViewHolder(
            ReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )

    override fun getItemCount(): Int = reviews.currentList.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews.currentList[position])
    }

    fun setData(reviews: List<Review>) {
        this.reviews.submitList(reviews)
    }

}