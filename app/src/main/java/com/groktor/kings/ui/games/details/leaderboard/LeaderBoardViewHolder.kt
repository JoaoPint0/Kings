package com.groktor.kings.ui.games.details.leaderboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.groktor.kings.R
import com.groktor.kings.model.Player
import kotlinx.android.synthetic.main.leaderboard_view_item.view.*

class LeaderBoardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private var player: Player? = null

    fun showBattleData(player: Player, position: Int) {
        this.player = player
        view.player_position.text = (position + 1).toString()
        view.player_name.text = player.account
        view.player_score.text = player.score.toString()

        when(position) {
            0 -> updateTextAccordingToPosition(19f)
            1 -> updateTextAccordingToPosition(18f)
            2 -> updateTextAccordingToPosition(17f)
            else -> Log.d("LeaderBoardViewHolder", "position$position")
        }

        view.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("accountId",player._id)
            view.findNavController().navigate(R.id.profileFragment, bundle)
        }
    }

    private fun updateTextAccordingToPosition(size:Float){

        view.player_position.textSize = size
        view.player_name.textSize = size
        view.player_score.textSize = size
    }

    companion object {
        fun create(parent: ViewGroup): LeaderBoardViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.leaderboard_view_item, parent, false)
            return LeaderBoardViewHolder(view)
        }
    }
}