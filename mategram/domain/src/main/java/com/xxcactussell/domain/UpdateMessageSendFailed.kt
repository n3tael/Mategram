package com.xxcactussell.domain

data class UpdateMessageSendFailed(
    val message: Message,
    val oldMessageId: Long,
    val error: Error
) : Update
