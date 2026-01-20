package com.xxcactussell.domain

data class RemoveNotificationGroup(
    val notificationGroupId: Int,
    val maxNotificationId: Int
) : Function
