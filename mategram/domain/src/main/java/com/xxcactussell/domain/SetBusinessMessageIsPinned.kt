package com.xxcactussell.domain

data class SetBusinessMessageIsPinned(
    val businessConnectionId: String,
    val chatId: Long,
    val messageId: Long,
    val isPinned: Boolean
) : Function
