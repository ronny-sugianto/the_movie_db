package id.ronnysugianto.themoviedb.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import id.ronnysugianto.themoviedb.data.Review
import id.ronnysugianto.themoviedb.databinding.ReviewItemBinding
import id.ronnysugianto.themoviedb.util.loadUrl

class ReviewViewHolder(
    private val binding: ReviewItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(review: Review) = binding.apply {
        tvTitle.text = review.author_details.name
        tvDescription.text = review.content
        review.author_details.avatar_path?.let { ivAvatar.loadUrl(it) }
    }
}