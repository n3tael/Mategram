package com.xxcactussell.domain

data class UpdateBusinessMessagesDeleted(
    val connectionId: String,
    val chatId: Long,
    val messageIds: LongArray
) : Update
