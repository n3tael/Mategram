package com.xxcactussell.domain

data class StarTransactionTypePaidMessageSend(
    val chatId: Long,
    val messageCount: Int
) : StarTransactionType
