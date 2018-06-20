package com.groktor.kings.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.groktor.kings.R
import com.groktor.kings.model.Battle
import kotlinx.android.synthetic.main.award_view_item.view.*

/**
 * View Holder for a [Battle] RecyclerView list item.
 */
class AwardsViewHolder(val view: View , val glide: RequestManager) : RecyclerView.ViewHolder(view) {

    private var battle: Battle? = null

    fun bind(battle: Battle?) {
        if (battle == null) {
            val resources = itemView.resources
            view.award_name.text = resources.getString(R.string.loading)
        } else {
            showBattleData(battle)
        }
    }

    private fun showBattleData(battle: Battle) {
        this.battle = battle
        view.award_name.text = battle.name

        glide.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8OG0VNOBJa3Ulf8drF-wbmJfNESjRqoDhH_afr3JuyW_Zkai5")
                .apply(RequestOptions.circleCropTransform())
                .into(view.award_logo)

    }

    companion object {
        fun create(parent: ViewGroup): AwardsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.award_view_item, parent, false)
            return AwardsViewHolder(view, Glide.with(parent.context))
        }
    }
}