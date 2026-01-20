package com.xxcactussell.domain

data class AddPendingPaidMessageReaction(
    val chatId: Long,
    val messageId: Long,
    val starCount: Long,
    val type: PaidReactionType
) : Function
