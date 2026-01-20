package com.xxcactussell.domain

data class NotificationTypeNewPushMessage(
    val messageId: Long,
    val senderId: MessageSender,
    val senderName: String,
    val isOutgoing: Boolean,
    val content: PushMessageContent
) : NotificationType
