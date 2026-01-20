package com.xxcactussell.domain

data class MessageOriginChannel(
    val chatId: Long,
    val messageId: Long,
    val authorSignature: String
) : MessageOrigin
