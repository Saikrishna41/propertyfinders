package com.techfortyone.propertyfinders.data

import com.techfortyone.propertyfinders.data.model.FacilityMainRealm
import com.techfortyone.propertyfinders.data.model.PropertyData
import com.techfortyone.propertyfinders.data.remote.PropertyServiceHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val propertServiceHelper: PropertyServiceHelper) {

    suspend fun getPropertyData(): PropertyData {
        return propertServiceHelper.getPropertyData()
    }
}