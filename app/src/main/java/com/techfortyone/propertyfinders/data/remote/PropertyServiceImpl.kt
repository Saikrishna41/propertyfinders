package com.techfortyone.propertyfinders.data.remote

import com.techfortyone.propertyfinders.data.model.FacilityMainRealm
import com.techfortyone.propertyfinders.data.model.PropertyData
import javax.inject.Inject

class PropertyServiceImpl @Inject constructor(private val propertyService: PropertyService) : PropertyServiceHelper {
    override suspend fun getPropertyData(): PropertyData {
        return propertyService.getPropertyData()
    }
}