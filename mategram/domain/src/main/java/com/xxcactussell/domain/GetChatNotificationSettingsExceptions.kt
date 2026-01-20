package com.xxcactussell.domain

data class GetChatNotificationSettingsExceptions(
    val scope: NotificationSettingsScope,
    val compareSound: Boolean
) : Function
