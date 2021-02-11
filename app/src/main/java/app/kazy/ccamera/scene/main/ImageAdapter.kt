package app.kazy.ccamera.scene.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.kazy.ccamera.databinding.ItemViewImageBinding
import com.bumptech.glide.Glide
import kotlin.properties.Delegates

typealias OnClickListener = (image: Image) -> Unit

class ImageAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
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
        Glide.with(holder.binding.root)
            .load(image.thumbnail)
            .into(holder.binding.imageView)
        holder.binding.imageView.setOnClickListener {
            onClickListener.invoke(image)
        }
    }

    override fun getItemCount(): Int = images.size
}
