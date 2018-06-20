package com.groktor.kings.ui.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.groktor.kings.R
import kotlinx.android.synthetic.main.intro_slide_item.view.*
import org.json.JSONObject

class SliderAdapter(val context: Context , private val onBoardingSlides :JSONObject) : PagerAdapter() {


    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view == `object`
    }

    override fun getCount(): Int {

        return onBoardingSlides.getJSONArray("onboarding").length()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.intro_slide_item,container,false)

        val slides = onBoardingSlides.getJSONArray("onboarding")

        val drawable = when(position){
            0-> R.drawable.ic_home
            1-> R.drawable.ic_camera
            2-> R.drawable.ic_games
            else-> R.drawable.ic_home
        }

        view.intro_slide_img.setImageResource(drawable)
        view.intro_slide_title.text = slides.getJSONObject(position).getString("title")
        view.intro_slide_description.text = slides.getJSONObject(position).getString("description")

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}