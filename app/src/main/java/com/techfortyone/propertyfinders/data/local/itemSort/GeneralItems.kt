package com.techfortyone.propertyfinders.data.local.itemSort

import com.techfortyone.propertyfinders.data.local.ListItem
import com.techfortyone.propertyfinders.data.model.Option

class GeneralItems(
    val options : Option
) : ListItem() {
    override fun getType(): Int {
        val type = ListItem.TYPE_LIST_ID_GENERAL_ITEM
        return type
    }
}