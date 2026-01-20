package com.xxcactussell.domain

data class SetChatNotificationSettings(
    val chatId: Long,
    val notificationSettings: ChatNotificationSettings
) : Function
