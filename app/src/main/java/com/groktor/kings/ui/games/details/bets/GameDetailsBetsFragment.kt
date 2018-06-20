package com.groktor.kings.ui.games.details.bets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.groktor.kings.Injection
import com.groktor.kings.R
import com.groktor.kings.ui.games.details.GameDetailsViewModel

class GameDetailsBetsFragment : Fragment() {

    companion object {
        fun newInstance() = GameDetailsBetsFragment()
    }

    private lateinit var viewModel: GameDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_details_bets_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, Injection.provideGameDetailsViewModelFactory(context!!))
                .get(GameDetailsViewModel::class.java)
    }

}
