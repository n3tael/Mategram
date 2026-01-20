package com.xxcactussell.domain

data class UpdateChatNotificationSettings(
    val chatId: Long,
    val notificationSettings: ChatNotificationSettings
) : Update
