package com.xxcactussell.domain

data class DeclineSuggestedPost(
    val chatId: Long,
    val messageId: Long,
    val comment: String
) : Function
