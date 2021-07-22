package com.yazantarifi.issuer.android.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.IssuerConfig
import com.yazantarifi.issuer.android.holders.ImageHolder
import com.yazantarifi.issuer.android.listeners.ImagesAdapterClickListener

class ImagesAdapter constructor(private val clickListeners: ImagesAdapterClickListener) :
    RecyclerView.Adapter<ImageHolder>() {

    companion object {
        const val ADD_IMAGE_TYPE = 1
        const val IMAGE_TYPE = 2
    }

    private val items: ArrayList<String> by lazy {
        ArrayList()
    }

    fun getAllImages(): ArrayList<String> {
        return items
    }

    fun addImage(image: String) {
        this.items.add(image)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            ADD_IMAGE_TYPE
        } else {
            IMAGE_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        if (viewType == IMAGE_TYPE) {
            return ImageHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image, null)
            )
        } else if (viewType == ADD_IMAGE_TYPE) {
            return ImageHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image_add, null)
            )
        } else {
            return ImageHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image, null)
            )
        }
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        try {
            if (position == itemCount - 1) {
                holder.addImageContainer?.setOnClickListener {
                    clickListeners.onAddImageClicked()
                }
            } else {
                holder.imagePath?.setImageDrawable(Drawable.createFromPath(items[position]))
                holder.imagePath?.setOnClickListener {
                    clickListeners.onImageClicked(items[position])
                }
            }
        } catch (ex: Exception) {
            IssuerConfig.getGlobalListener()?.onErrorTriggered(ex)
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

}
