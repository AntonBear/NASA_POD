package com.anton.nasa_pod

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anton.nasa_pod.databinding.ItemImageBinding

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemImageBinding.bind(itemView)
    val imageView = binding.imageView
}