package com.xxcactussell.domain

data class MessageLocation(
    val location: Location,
    val livePeriod: Int,
    val expiresIn: Int,
    val heading: Int,
    val proximityAlertRadius: Int
) : MessageContent
