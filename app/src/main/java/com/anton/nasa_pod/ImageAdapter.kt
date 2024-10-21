package com.anton.nasa_pod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anton.nasa_pod.databinding.ItemImageBinding

class ImageAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        Glide.with(holder.itemView.context)
//            .load(imageUrls[position])
//            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int = imageUrls.size
}