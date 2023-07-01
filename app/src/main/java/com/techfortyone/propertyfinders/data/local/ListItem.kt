package com.techfortyone.propertyfinders.data.local

abstract class ListItem {
    companion object {

        val TYPE_LIST_ID_GENERAL_ITEM = 0

        val TYPE_LIST_ID_HEADER_ITEM = 1
    }

    abstract fun getType() : Int
}