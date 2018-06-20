package com.groktor.kings.ui.games.details.market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.groktor.kings.Injection
import com.groktor.kings.R
import com.groktor.kings.ui.games.details.GameDetailsViewModel
import kotlinx.android.synthetic.main.game_details_market_fragment.view.*

class GameDetailsMarketFragment : Fragment() {

    private lateinit var viewModel: GameDetailsViewModel
    private var listDataHeader = mutableListOf<String>()
    private var listDataChild  = HashMap<String, List<String>>();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.game_details_market_fragment, container, false)

        prepareListData()

        view.market_list.setAdapter(MarketExpandableListAdapter(context!!, listDataHeader , listDataChild))

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, Injection.provideGameDetailsViewModelFactory(context!!))
                .get(GameDetailsViewModel::class.java)
    }

    private fun prepareListData() {

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        val top250 = mutableListOf<String>()
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        val nowShowing = mutableListOf<String>()
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        val comingSoon = mutableListOf<String>()
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}
