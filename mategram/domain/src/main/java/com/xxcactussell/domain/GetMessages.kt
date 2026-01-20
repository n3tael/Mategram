package com.xxcactussell.domain

data class GetMessages(
    val chatId: Long,
    val messageIds: LongArray
) : Function
