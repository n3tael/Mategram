package com.xxcactussell.domain

data class GetForumTopicLink(
    val chatId: Long,
    val messageThreadId: Long
) : Function
