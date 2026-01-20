package com.xxcactussell.domain

data class MessageProximityAlertTriggered(
    val travelerId: MessageSender,
    val watcherId: MessageSender,
    val distance: Int
) : MessageContent
