package com.xxcactussell.domain

data class InputMessageLocation(
    val location: Location,
    val livePeriod: Int,
    val heading: Int,
    val proximityAlertRadius: Int
) : InputMessageContent
