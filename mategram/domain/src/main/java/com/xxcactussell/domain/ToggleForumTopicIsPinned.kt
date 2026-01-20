package com.xxcactussell.domain

data class ToggleForumTopicIsPinned(
    val chatId: Long,
    val messageThreadId: Long,
    val isPinned: Boolean
) : Function
