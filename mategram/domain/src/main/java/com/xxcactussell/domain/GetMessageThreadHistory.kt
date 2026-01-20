package com.xxcactussell.domain

data class GetMessageThreadHistory(
    val chatId: Long,
    val messageId: Long,
    val fromMessageId: Long,
    val offset: Int,
    val limit: Int
) : Function
