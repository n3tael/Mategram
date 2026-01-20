package com.xxcactussell.domain

data class SetPaidMessageReactionType(
    val chatId: Long,
    val messageId: Long,
    val type: PaidReactionType
) : Function
