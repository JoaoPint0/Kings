package com.groktor.kings.ui.games.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.groktor.kings.R
import kotlinx.android.synthetic.main.game_details_fragment.view.*

class GameDetailsFragment : Fragment() {

    val TAG :String = "GameDetailsFragment"

    companion object {
        fun newInstance() = GameDetailsFragment()
    }

    private lateinit var viewModel: GameDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.game_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = arguments?.let {
            val safeArgs = GameDetailsFragmentArgs.fromBundle(it)
            safeArgs.gameId
        }

        Log.e(TAG, "$gameId")

        view.gameId.text = "Game $gameId"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
