package com.xxcactussell.domain

data class SetPinnedForumTopics(
    val chatId: Long,
    val messageThreadIds: LongArray
) : Function
