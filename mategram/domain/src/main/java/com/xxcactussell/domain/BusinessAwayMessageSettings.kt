package com.xxcactussell.domain

data class BusinessAwayMessageSettings(
    val shortcutId: Int,
    val recipients: BusinessRecipients,
    val schedule: BusinessAwayMessageSchedule,
    val offlineOnly: Boolean
) : Object
