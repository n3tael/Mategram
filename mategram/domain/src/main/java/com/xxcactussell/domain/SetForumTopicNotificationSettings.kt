package com.xxcactussell.domain

data class SetForumTopicNotificationSettings(
    val chatId: Long,
    val messageThreadId: Long,
    val notificationSettings: ChatNotificationSettings
) : Function
