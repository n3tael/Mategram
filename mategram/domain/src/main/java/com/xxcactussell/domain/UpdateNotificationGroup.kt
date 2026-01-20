package com.xxcactussell.domain

data class UpdateNotificationGroup(
    val notificationGroupId: Int,
    val type: NotificationGroupType,
    val chatId: Long,
    val notificationSettingsChatId: Long,
    val notificationSoundId: Long,
    val totalCount: Int,
    val addedNotifications: List<Notification>,
    val removedNotificationIds: IntArray
) : Update
