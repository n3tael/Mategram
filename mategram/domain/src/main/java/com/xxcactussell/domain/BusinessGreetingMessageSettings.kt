package com.xxcactussell.domain

data class BusinessGreetingMessageSettings(
    val shortcutId: Int,
    val recipients: BusinessRecipients,
    val inactivityDays: Int
) : Object
