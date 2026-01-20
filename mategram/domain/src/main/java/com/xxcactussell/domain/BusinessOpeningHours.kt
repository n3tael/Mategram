package com.xxcactussell.domain

data class BusinessOpeningHours(
    val timeZoneId: String,
    val openingHours: List<BusinessOpeningHoursInterval>
) : Object
