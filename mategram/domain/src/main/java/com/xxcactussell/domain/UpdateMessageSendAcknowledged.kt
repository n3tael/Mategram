package com.xxcactussell.domain

data class UpdateMessageSendAcknowledged(
    val chatId: Long,
    val messageId: Long
) : Update
