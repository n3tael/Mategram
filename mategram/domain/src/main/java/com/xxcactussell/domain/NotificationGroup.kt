package com.xxcactussell.domain

data class NotificationGroup(
    val id: Int,
    val type: NotificationGroupType,
    val chatId: Long,
    val totalCount: Int,
    val notifications: List<Notification>
) : Object
