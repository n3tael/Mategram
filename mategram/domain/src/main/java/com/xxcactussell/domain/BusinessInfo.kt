package com.xxcactussell.domain

data class BusinessInfo(
    val location: BusinessLocation? = null,
    val openingHours: BusinessOpeningHours? = null,
    val localOpeningHours: BusinessOpeningHours? = null,
    val nextOpenIn: Int,
    val nextCloseIn: Int,
    val greetingMessageSettings: BusinessGreetingMessageSettings? = null,
    val awayMessageSettings: BusinessAwayMessageSettings? = null,
    val startPage: BusinessStartPage? = null
) : Object
