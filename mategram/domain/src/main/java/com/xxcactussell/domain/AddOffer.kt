package com.xxcactussell.domain

data class AddOffer(
    val chatId: Long,
    val messageId: Long,
    val options: MessageSendOptions
) : Function
