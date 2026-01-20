package com.xxcactussell.domain

data class PushMessageContentProximityAlertTriggered(
    val distance: Int
) : PushMessageContent
