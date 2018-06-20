package com.groktor.kings.ui.profile

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.groktor.kings.model.Battle

/**
 * Adapter for the list of awards.
 */
class AwardsAdapter : PagedListAdapter<Battle, RecyclerView.ViewHolder>(AWARD_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AwardsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val awardItem = getItem(position)

        if (awardItem != null) {
            (holder as AwardsViewHolder).bind(awardItem)
        }
    }

    companion object {
        private val AWARD_COMPARATOR = object : DiffUtil.ItemCallback<Battle>() {
            override fun areItemsTheSame(oldItem: Battle, newItem: Battle): Boolean =
                    oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Battle, newItem: Battle): Boolean =
                    oldItem == newItem
        }
    }
}