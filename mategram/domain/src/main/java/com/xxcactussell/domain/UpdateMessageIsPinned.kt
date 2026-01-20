package com.xxcactussell.domain

data class UpdateMessageIsPinned(
    val chatId: Long,
    val messageId: Long,
    val isPinned: Boolean
) : Update
