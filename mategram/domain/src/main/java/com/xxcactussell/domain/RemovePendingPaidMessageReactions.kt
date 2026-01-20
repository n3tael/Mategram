package com.xxcactussell.domain

data class RemovePendingPaidMessageReactions(
    val chatId: Long,
    val messageId: Long
) : Function
