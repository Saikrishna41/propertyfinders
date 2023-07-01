package com.techfortyone.propertyfinders.data.local.itemSort

import com.techfortyone.propertyfinders.data.local.ListItem

class HeaderId(val id : String) : ListItem() {
    override fun getType(): Int {
        var type = ListItem.TYPE_LIST_ID_HEADER_ITEM
        return type
    }
}