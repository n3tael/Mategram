package com.xxcactussell.domain

data class SetChatPaidMessageStarCount(
    val chatId: Long,
    val paidMessageStarCount: Long
) : Function
