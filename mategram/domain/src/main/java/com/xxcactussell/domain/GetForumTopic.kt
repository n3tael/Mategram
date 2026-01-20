package com.xxcactussell.domain

data class GetForumTopic(
    val chatId: Long,
    val messageThreadId: Long
) : Function
