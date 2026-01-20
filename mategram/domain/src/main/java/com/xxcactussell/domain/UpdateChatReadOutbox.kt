package com.xxcactussell.domain

data class UpdateChatReadOutbox(
    val chatId: Long,
    val lastReadOutboxMessageId: Long
) : Update
