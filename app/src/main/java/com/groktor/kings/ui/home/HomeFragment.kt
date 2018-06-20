package com.groktor.kings.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.groktor.kings.Injection
import com.groktor.kings.R
import com.groktor.kings.model.Battle
import kotlinx.android.synthetic.main.home_fragment.view.*


class HomeFragment : Fragment() {

    private val eventsAdapter = EventsAdapter()
    private val chatsAdapter = ChatsAdapter()


    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return  inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, Injection.provideHomeViewModelFactory(context!!))
                .get(HomeViewModel::class.java)

        initAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.new_event_btn.setOnClickListener {
            view.findNavController().navigate(R.id.createGameFragment, null)
        }
    }

    private fun initAdapter() {
        view!!.findViewById<RecyclerView>(R.id.event_list).apply {
            this.adapter = eventsAdapter
        }

        viewModel.battles.observe(this, Observer<PagedList<Battle>> {
            Log.d("HomeFragment", "list: ${it?.size}")
            //showEmptyList(it?.size == 0)
            eventsAdapter.submitList(it)
        })

        view!!.findViewById<RecyclerView>(R.id.chat_list).apply {
            this.adapter = chatsAdapter
        }

        viewModel.battles.observe(this, Observer<PagedList<Battle>> {
            Log.d("HomeFragment", "list: ${it?.size}")
            //showEmptyList(it?.size == 0)
            chatsAdapter.submitList(it)
        })

    }
}
