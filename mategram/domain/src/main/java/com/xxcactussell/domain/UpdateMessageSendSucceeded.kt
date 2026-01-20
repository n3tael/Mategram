package com.xxcactussell.domain

data class UpdateMessageSendSucceeded(
    val message: Message,
    val oldMessageId: Long
) : Update
