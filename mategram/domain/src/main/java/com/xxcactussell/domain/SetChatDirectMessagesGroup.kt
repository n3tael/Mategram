package com.xxcactussell.domain

data class SetChatDirectMessagesGroup(
    val chatId: Long,
    val isEnabled: Boolean,
    val paidMessageStarCount: Long
) : Function
