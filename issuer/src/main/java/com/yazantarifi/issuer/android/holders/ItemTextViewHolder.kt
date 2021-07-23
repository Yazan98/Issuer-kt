package com.yazantarifi.issuer.android.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_text.view.*
import kotlinx.android.synthetic.main.item_text_title.view.*

class ItemTextViewHolder constructor(view: View): RecyclerView.ViewHolder(view) {
    val textTitle: TextView? = view.textTitle
    val textBody: TextView? = view.textBody
}