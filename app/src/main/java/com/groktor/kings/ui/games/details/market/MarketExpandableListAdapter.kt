package com.groktor.kings.ui.games.details.market

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.groktor.kings.R
import kotlinx.android.synthetic.main.market_view_group_title.view.*
import kotlinx.android.synthetic.main.market_view_item.view.*

class MarketExpandableListAdapter( private val context: Context, private val headers: List<String>, private val children : HashMap<String, List<String>>) : BaseExpandableListAdapter() {

    override fun getGroup(position: Int): Any {
        return headers[position]
    }

    override fun isChildSelectable(headerPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpandable: Boolean, convertView: View?, parent: ViewGroup?): View {
        val headerTitle = headers[groupPosition]

        val view = LayoutInflater.from(context).inflate(R.layout.market_view_group_title,parent,false)

        view.market_header.text = headerTitle

        return  view
    }

    override fun getChildrenCount(groupPosition:  Int): Int {
       return children[headers[groupPosition]]?.size ?: 0
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return children[headers[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {

        val childText = getChild(groupPosition, childPosition) as String

        val view = LayoutInflater.from(context).inflate(R.layout.market_view_item,parent,false)

        view.market_item.text = childText

        return  view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return headers.size
    }
}