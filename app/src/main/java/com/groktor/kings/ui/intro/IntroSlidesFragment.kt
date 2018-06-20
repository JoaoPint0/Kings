package com.groktor.kings.ui.intro

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.groktor.kings.R

class IntroSlidesFragment : Fragment() {

    companion object {
        fun newInstance() = IntroSlidesFragment()
    }

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

}
