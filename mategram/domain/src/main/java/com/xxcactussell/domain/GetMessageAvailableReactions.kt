package com.xxcactussell.domain

data class GetMessageAvailableReactions(
    val chatId: Long,
    val messageId: Long,
    val rowSize: Int
) : Function
