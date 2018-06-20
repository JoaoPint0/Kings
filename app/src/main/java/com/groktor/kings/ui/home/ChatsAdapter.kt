package com.groktor.kings.ui.home

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.groktor.kings.model.Battle

/**
 * Adapter for the list of chats.
 */
class ChatsAdapter : PagedListAdapter<Battle, RecyclerView.ViewHolder>(CHAT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChatsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatItem = getItem(position)
        if (chatItem != null) {
            (holder as ChatsViewHolder).bind(chatItem)
        }
    }

    companion object {
        private val CHAT_COMPARATOR = object : DiffUtil.ItemCallback<Battle>() {
            override fun areItemsTheSame(oldItem: Battle, newItem: Battle): Boolean =
                    oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Battle, newItem: Battle): Boolean =
                    oldItem == newItem
        }
    }
}