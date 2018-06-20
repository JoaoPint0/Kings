package com.groktor.kings.ui.games.details.leaderboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.groktor.kings.Injection
import com.groktor.kings.R
import com.groktor.kings.model.Battle
import com.groktor.kings.model.Player
import com.groktor.kings.ui.games.details.GameDetailsViewModel
import kotlinx.android.synthetic.main.game_details_leaderboard_fragment.view.*

class GameDetailsLeaderboardFragment : Fragment() {

    private lateinit var viewModel: GameDetailsViewModel
    private val adapter = LeaderBoardAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.game_details_leaderboard_fragment, container, false)

        view.leaderboard_list.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, Injection.provideGameDetailsViewModelFactory(context!!))
                .get(GameDetailsViewModel::class.java)


        viewModel.searchBattle("5b27dcec71169b02bd16bcc7").observe(this, Observer<Battle> {
            Log.e("LeaderBoardFragment", "battle: ${it?._id}")

        })

        viewModel.players.observe(this, Observer<List<Player>> {
            Log.e("LeaderBoardFragment", "player: ${it?.size}")
            adapter.setPlayers(it ?: emptyList())
        })
    }
}
