package com.techfortyone.propertyfinders.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techfortyone.propertyfinders.R
import com.techfortyone.propertyfinders.data.local.ListItem
import com.techfortyone.propertyfinders.data.local.itemSort.GeneralItems
import com.techfortyone.propertyfinders.data.local.itemSort.HeaderId
import com.techfortyone.propertyfinders.data.model.FacilityAndOptions

class MyPropertyRecyclerView : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mConsolidatedList = emptyList<ListItem>()
    private lateinit var generalItem: GeneralItems
    private lateinit var headerItem: HeaderId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        when (viewType) {
            ListItem.TYPE_LIST_ID_HEADER_ITEM -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.facility_item, parent, false)
                return MyViewHeadHolder(view)
            }

            ListItem.TYPE_LIST_ID_GENERAL_ITEM -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
                return MyViewItemHolder(view)

            }

            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.facility_item, parent, false)
                return MyViewHeadHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mConsolidatedList[position]
        when (getItemViewType(position)) {
            ListItem.TYPE_LIST_ID_HEADER_ITEM -> {
                headerItem = data as HeaderId
                val headerItemViewHolder: MyViewHeadHolder = holder as MyViewHeadHolder
                headerItemViewHolder.let {
                    it.headTitle.text = headerItem.id.toString()
                }
            }

            ListItem.TYPE_LIST_ID_GENERAL_ITEM -> {
                generalItem = data as GeneralItems
                val generalItemsHolder: MyViewItemHolder = holder as MyViewItemHolder
                generalItemsHolder.let {
                    it.options.text = generalItem.options.toString()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mConsolidatedList.size
    }

    fun setData(consolidatedList: MutableList<ListItem>) {
        mConsolidatedList = consolidatedList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return mConsolidatedList.get(position).getType()
    }

    inner class MyViewHeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headTitle = itemView.findViewById<TextView>(R.id.fItemTv)

    }


    inner class MyViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val options = itemView.findViewById<TextView>(R.id.optionItemTv)
    }

}