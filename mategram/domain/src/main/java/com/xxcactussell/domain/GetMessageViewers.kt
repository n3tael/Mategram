package com.xxcactussell.domain

data class GetMessageViewers(
    val chatId: Long,
    val messageId: Long
) : Function
