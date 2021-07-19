package com.yazantarifi.issuer.android.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.android.synthetic.main.item_image_add.view.*

class ImageHolder constructor(view: View): RecyclerView.ViewHolder(view) {
    val addImageContainer = view.addImageContainer
    val imagePath = view.imagePath
}