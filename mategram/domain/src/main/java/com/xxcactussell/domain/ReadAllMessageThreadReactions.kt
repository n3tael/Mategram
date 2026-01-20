package com.xxcactussell.domain

data class ReadAllMessageThreadReactions(
    val chatId: Long,
    val messageThreadId: Long
) : Function
