package com.xxcactussell.domain

data class ToggleForumTopicIsClosed(
    val chatId: Long,
    val messageThreadId: Long,
    val isClosed: Boolean
) : Function
