package com.techfortyone.propertyfinders.data.model

data class FacilityAndOptions(
    val facility_id : String,
    val name : String,
    val option: List<Option>
)