package com.xxcactussell.domain

data class GetMessageLocally(
    val chatId: Long,
    val messageId: Long
) : Function
