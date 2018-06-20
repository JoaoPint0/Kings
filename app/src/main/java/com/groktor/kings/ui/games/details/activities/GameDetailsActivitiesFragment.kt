package com.groktor.kings.ui.games.details.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.groktor.kings.Injection
import com.groktor.kings.R
import com.groktor.kings.ui.games.details.GameDetailsViewModel

class GameDetailsActivitiesFragment : Fragment() {

    companion object {
        fun newInstance() = GameDetailsActivitiesFragment()
    }

    private lateinit var viewModel: GameDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.e("Activities Fragment","createView")
        return inflater.inflate(R.layout.game_details_activities_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, Injection.provideGameDetailsViewModelFactory(context!!))
                .get(GameDetailsViewModel::class.java)

        Log.e("Activities Fragment","activityCreated")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Activities Fragment","viewCreated")
    }

}
