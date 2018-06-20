package com.groktor.kings.ui.games.create

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.groktor.kings.R
import kotlinx.android.synthetic.main.create_game_fragment.view.*

class CreateGameFragment : Fragment() {

    companion object {
        fun newInstance() = CreateGameFragment()
    }

    private lateinit var viewModel: CreateGameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_game_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.create_game_btn.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("gameId","Created")
            view.findNavController().navigate(R.id.view_game_action, bundle)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateGameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
