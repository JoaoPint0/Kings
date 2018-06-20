package com.groktor.kings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.groktor.kings.R
import com.groktor.kings.model.Battle
import kotlinx.android.synthetic.main.chat_view_item.view.*

/**
 * View Holder for a [Battle] RecyclerView list item.
 */
class ChatsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val chatName: TextView = view.chat_name

    private var battle: Battle? = null


    fun bind(battle: Battle?) {
        if (battle == null) {
            val resources = itemView.resources
            chatName.text = resources.getString(R.string.loading)
        } else {
            showBattleData(battle)
        }
    }

    private fun showBattleData(battle: Battle) {
        this.battle = battle
        chatName.text = battle.name

        view.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("gameId","${battle._id}")
            view.findNavController().navigate(R.id.gameDetailsFragment, bundle)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ChatsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_view_item, parent, false)
            return ChatsViewHolder(view)
        }
    }
}