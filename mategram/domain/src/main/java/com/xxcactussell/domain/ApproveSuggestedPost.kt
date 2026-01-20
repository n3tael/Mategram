package com.xxcactussell.domain

data class ApproveSuggestedPost(
    val chatId: Long,
    val messageId: Long,
    val sendDate: Int
) : Function
