package com.xxcactussell.domain

data class GetRepliedMessage(
    val chatId: Long,
    val messageId: Long
) : Function
