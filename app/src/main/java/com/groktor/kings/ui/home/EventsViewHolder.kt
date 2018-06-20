/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.groktor.kings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.groktor.kings.R
import com.groktor.kings.model.Battle
import kotlinx.android.synthetic.main.event_view_item.view.*


/**
 * View Holder for a [Battle] RecyclerView list item.
 */
class EventsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val eventName: TextView = view.event_name

    private var battle: Battle? = null

    fun bind(battle: Battle?) {
        if (battle == null) {
            val resources = itemView.resources
            eventName.text = resources.getString(R.string.loading)
        } else {
            showBattleData(battle)
        }
    }

    private fun showBattleData(battle: Battle) {
        this.battle = battle
        eventName.text = battle.name

        view.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("gameId","${battle._id}")
            view.findNavController().navigate(R.id.gameDetailsFragment, bundle)
        }
    }

    companion object {
        fun create(parent: ViewGroup): EventsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.event_view_item, parent, false)
            return EventsViewHolder(view)
        }
    }
}