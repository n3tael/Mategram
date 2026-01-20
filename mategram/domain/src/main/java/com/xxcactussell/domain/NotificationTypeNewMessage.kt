package com.xxcactussell.domain

data class NotificationTypeNewMessage(
    val message: Message,
    val showPreview: Boolean
) : NotificationType
