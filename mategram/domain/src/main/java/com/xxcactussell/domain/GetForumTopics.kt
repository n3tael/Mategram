package com.xxcactussell.domain

data class GetForumTopics(
    val chatId: Long,
    val query: String,
    val offsetDate: Int,
    val offsetMessageId: Long,
    val offsetMessageThreadId: Long,
    val limit: Int
) : Function
