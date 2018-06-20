package com.groktor.kings.ui.intro

import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.groktor.kings.R
import kotlinx.android.synthetic.main.intro_slides_fragment.view.*
import org.json.JSONObject

class IntroSlidesFragment : Fragment() {

    private lateinit var viewModel: IntroSlidesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.intro_slides_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IntroSlidesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = JSONObject("{\n" +
                "\t\"onboarding\": [{\n" +
                "\t\t\t\"title\": \"Page 1\",\n" +
                "\t\t\t\"description\": \"description 1\",\n" +
                "\t\t\t\"drawable\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoqDSVe6yXohFX6FTGUot5jEpzuFfc7bnjfLGcekKk-NbT1dbKSQ\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"Page 2\",\n" +
                "\t\t\t\"description\": \"description 2\",\n" +
                "\t\t\t\"drawable\": \"https://cdn3.iconfinder.com/data/icons/teamwork/355/friends-friendship-partner-team-teamwork-006-512.png\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"Page 3\",\n" +
                "\t\t\t\"description\": \"description 3\",\n" +
                "\t\t\t\"drawable\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTybE_o51r_-0MoN4tTkpkqQBgq2ib5YPe19_jmdPCEtvl5Kt1C\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}")

        val sliderAdapter = SliderAdapter(context!!, json )
        view.container.adapter = sliderAdapter

        view.container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

                updateIndicators(view, position)

                view.intro_btn_previous.visibility = if (position == 0) View.GONE else View.VISIBLE
                view.intro_btn_skip.visibility = if (position == 0) View.VISIBLE else View.GONE

                view.intro_btn_next.visibility = if (position == 2) View.GONE else View.VISIBLE
                view.intro_btn_finish.visibility = if (position == 2) View.VISIBLE else View.GONE

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        view.intro_btn_previous.setOnClickListener{

            view.container.currentItem -= 1
        }

        view.intro_btn_next.setOnClickListener{

            view.container.currentItem += 1
        }

        view.intro_btn_skip.setOnClickListener{

            exitOnBoard()
        }

        view.intro_btn_finish.setOnClickListener{

            exitOnBoard()
        }
    }

    private fun exitOnBoard(){

        val sharedPref = this.activity?.getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE)

        sharedPref?.edit()?.putBoolean(getString(R.string.intro_slides), false)?.apply()
        findNavController(this ).navigate(R.id.pick_user_name_action)
    }

    private fun updateIndicators(view: View, position: Int) {
        for (i in 0..2) {
            view.intro_dots_indicator.getChildAt(i).background =
                    ContextCompat.getDrawable(context!!, if (i == position) R.drawable.indicator_selected else R.drawable.indicator_unselected)
        }
    }
}
