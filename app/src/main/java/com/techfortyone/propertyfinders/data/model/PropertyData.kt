package com.techfortyone.propertyfinders.data.model

data class PropertyData(
    val exclusions: List<List<Exclusion>>,
    val facilities: List<Facility>
)