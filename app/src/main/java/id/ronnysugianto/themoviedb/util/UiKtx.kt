package id.ronnysugianto.themoviedb.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import id.ronnysugianto.themoviedb.BuildConfig
import id.ronnysugianto.themoviedb.MainApp
import id.ronnysugianto.themoviedb.R

val Context.appComponent
    inline get() = (applicationContext as MainApp).appComponent

fun ImageView.loadUrl(url: String) {
    val picasso = Picasso.Builder(context)
        .memoryCache(LruCache(24000000))
        .defaultBitmapConfig(Bitmap.Config.RGB_565)
        .build()

    val imageUrl = if(url.contains("https")) url else BuildConfig.IMAGE_BASE_URL + url

    picasso.load(imageUrl).resize(1080,600).centerCrop()
        .placeholder(R.drawable.genre_rounded_background).error(R.drawable.error)
        .priority(Picasso.Priority.HIGH).into(this)
}

interface ItemClickListener<in T> {
    fun onItemClick(data: T)
}

open class DiffCallback<T : Any>(
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSame.invoke(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areContentsTheSame.invoke(oldItem, newItem)
    }
}

