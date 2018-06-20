package com.groktor.kings.ui.games.details

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.groktor.kings.ui.games.details.activities.GameDetailsActivitiesFragment
import com.groktor.kings.ui.games.details.bets.GameDetailsBetsFragment
import com.groktor.kings.ui.games.details.leaderboard.GameDetailsLeaderboardFragment
import com.groktor.kings.ui.games.details.market.GameDetailsMarketFragment

class GameDetailsFragmentPagerAdapter(fragmentManager: FragmentManager?, private val tittles:List<String>): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        Log.e("Adapater", "$position")
        return when (position) {
            0 ->  GameDetailsLeaderboardFragment()
            1 ->  GameDetailsActivitiesFragment()
            2 ->  GameDetailsMarketFragment()
            3 ->  GameDetailsBetsFragment()
            else ->  GameDetailsActivitiesFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tittles[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (position >= count) {
            val manager = (`object` as Fragment).fragmentManager
            val trans = manager!!.beginTransaction()
            trans.remove(`object`)
            trans.commit()
        }
    }
}