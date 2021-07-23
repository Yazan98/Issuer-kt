package com.yazantarifi.issuer.android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.data.OptionItem
import com.yazantarifi.issuer.android.holders.ItemTextViewHolder
import com.yazantarifi.issuer.android.listeners.OptionSelectionListener

class OptionsAdapter constructor(
    private val items: ArrayList<OptionItem>,
    private val listener: OptionSelectionListener
): RecyclerView.Adapter<ItemTextViewHolder>() {

    companion object {
        const val HEADER_ITEM = 1
        const val OPTION_ITEM = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isHeader) {
            HEADER_ITEM
        } else {
            OPTION_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTextViewHolder {
        return if (viewType == HEADER_ITEM) {
            ItemTextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text_title, null, false))
        } else {
            ItemTextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text, null, false))
        }
    }

    override fun onBindViewHolder(holder: ItemTextViewHolder, position: Int) {
        val currentItem = items[position]
        holder.textTitle?.text = currentItem.value
        holder.textBody?.text = currentItem.value

        holder.textTitle?.setOnClickListener {
            listener.onOptionSelected(currentItem.value ?: "")
        }

        holder.textBody?.setOnClickListener {
            listener.onOptionSelected(currentItem.value ?: "")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}