package com.xxcactussell.domain

data class SetChatMessageSender(
    val chatId: Long,
    val messageSenderId: MessageSender
) : Function
