package com.xxcactussell.domain

data class UpdateNotification(
    val notificationGroupId: Int,
    val notification: Notification
) : Update
