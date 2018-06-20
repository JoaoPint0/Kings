package com.groktor.kings.ui.games.details.leaderboard

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.groktor.kings.model.Player

class LeaderBoardAdapter : RecyclerView.Adapter<LeaderBoardViewHolder>()  {

    var playersList : List<Player> = emptyList()

    fun setPlayers(roomGates: List<Player>) {
        playersList = roomGates.sortedByDescending { it.score }
        Log.e("Recycler Adapter",playersList.toString())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int) {

        val player = playersList[position]
        holder.showBattleData(player, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder {
        return LeaderBoardViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
       return playersList.size
    }

}