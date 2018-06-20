package com.groktor.kings.ui.games.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.groktor.kings.Injection
import com.groktor.kings.R
import com.groktor.kings.model.Battle
import kotlinx.android.synthetic.main.game_details_fragment.*
import kotlinx.android.synthetic.main.game_details_fragment.view.*
import java.text.SimpleDateFormat


class GameDetailsFragment : Fragment() {

    private val TAG :String = "GameDetailsFragment"
    private lateinit var viewModel: GameDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.game_details_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"viewCreated")

        val tabTittles = listOf(
                getString(R.string.game_details_tab_leaderboard),
                getString(R.string.game_details_tab_activities),
                getString(R.string.game_details_tab_market),
                getString(R.string.game_details_tab_bet))

        val slideAdapter = GameDetailsFragmentPagerAdapter(childFragmentManager,tabTittles)

        pager.adapter = slideAdapter
        pager.currentItem = 1

        sliding_tabs.setupWithViewPager(pager)

        val options = RequestOptions()

        Glide.with(view.context)
                .load("https://i.kinja-img.com/gawker-media/image/upload/s--FikCm0ZG--/c_scale,f_auto,fl_progressive,q_80,w_800/v9znu9yvmw9zz22zkzlu.jpg")
                .apply(options.centerInside())
                .into(view.battle_logo)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, Injection.provideGameDetailsViewModelFactory(context!!))
                .get(GameDetailsViewModel::class.java)

        val gameId = arguments?.let {
            val safeArgs = GameDetailsFragmentArgs.fromBundle(it)
            safeArgs.gameId
        }
        gameId ?: return
        viewModel.searchBattle(gameId).observe(this, Observer<Battle> {
            Log.e("LeaderBoardFragment", "battle: ${it?._id}")

            game_details_title.text = it?.name
            val format = SimpleDateFormat("hh:mm dd/MM/yyy")
            game_details_status.text = "Ends at ${format.format(it?.finishesAt)}"
        })

        Log.e(TAG, "$gameId")
    }


}
