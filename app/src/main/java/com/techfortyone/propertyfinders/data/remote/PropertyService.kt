package com.techfortyone.propertyfinders.data.remote

import com.techfortyone.propertyfinders.data.model.FacilityMainRealm
import com.techfortyone.propertyfinders.data.model.PropertyData
import retrofit2.http.GET

interface PropertyService {

    @GET("iranjith4/ad-assignment/db")
    suspend fun getPropertyData() : PropertyData
}