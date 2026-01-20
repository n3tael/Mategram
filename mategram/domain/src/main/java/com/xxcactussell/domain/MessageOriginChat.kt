package com.xxcactussell.domain

data class MessageOriginChat(
    val senderChatId: Long,
    val authorSignature: String
) : MessageOrigin
