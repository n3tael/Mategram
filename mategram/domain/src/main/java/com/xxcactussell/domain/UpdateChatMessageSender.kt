package com.xxcactussell.domain

data class UpdateChatMessageSender(
    val chatId: Long,
    val messageSenderId: MessageSender? = null
) : Update
