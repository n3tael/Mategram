package com.xxcactussell.domain

data class ToggleDirectMessagesChatTopicCanSendUnpaidMessages(
    val chatId: Long,
    val topicId: Long,
    val canSendUnpaidMessages: Boolean,
    val refundPayments: Boolean
) : Function
