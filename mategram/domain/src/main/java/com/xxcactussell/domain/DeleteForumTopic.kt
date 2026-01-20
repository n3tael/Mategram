package com.xxcactussell.domain

data class DeleteForumTopic(
    val chatId: Long,
    val messageThreadId: Long
) : Function
