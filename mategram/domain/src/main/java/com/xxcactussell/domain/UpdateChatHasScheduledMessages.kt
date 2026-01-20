package com.xxcactussell.domain

data class UpdateChatHasScheduledMessages(
    val chatId: Long,
    val hasScheduledMessages: Boolean
) : Update
