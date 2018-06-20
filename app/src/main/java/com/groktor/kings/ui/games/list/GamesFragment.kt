package com.groktor.kings.ui.games.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.groktor.kings.R
import kotlinx.android.synthetic.main.games_fragment.view.*

class GamesFragment : Fragment() {

    companion object {
        fun newInstance() = GamesFragment()
    }

    private lateinit var viewModel: GamesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.games_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.new_game_btn.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.new_game_action, null)
        )

        view.view_game_btn.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("gameId","13")
            view.findNavController().navigate(R.id.view_game_action, bundle)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
