package com.xxcactussell.domain

data class ReadAllMessageThreadMentions(
    val chatId: Long,
    val messageThreadId: Long
) : Function
