package com.example.homework_m5_3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework_m5_3.databinding.ItemImageBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    public var listImage = ArrayList<ImageModel>()
    class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: ImageModel) {
            with(binding) {
                tvLikes.text = "Likes: ${image.likes}"
                ivImage.load(image.largeImageURL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listImage.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listImage[position])
    }
    fun updateList(newImagesList: List<ImageModel>) {
        listImage.clear()
        listImage.addAll(newImagesList)
        notifyDataSetChanged()
    }
}