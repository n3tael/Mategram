package com.xxcactussell.domain

data class ForwardSource(
    val chatId: Long,
    val messageId: Long,
    val senderId: MessageSender? = null,
    val senderName: String,
    val date: Int,
    val isOutgoing: Boolean
) : Object
