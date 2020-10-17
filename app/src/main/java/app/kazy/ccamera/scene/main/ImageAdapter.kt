package app.kazy.ccamera.scene.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.kazy.ccamera.databinding.ItemViewImageBinding
import coil.load
import kotlin.properties.Delegates

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    var images: List<Image> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemViewImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewImageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        holder.binding.imageView.load(image.thumbnail)
    }

    override fun getItemCount(): Int = images.size
}
