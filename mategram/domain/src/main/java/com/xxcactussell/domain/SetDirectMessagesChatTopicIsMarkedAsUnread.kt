package com.xxcactussell.domain

data class SetDirectMessagesChatTopicIsMarkedAsUnread(
    val chatId: Long,
    val topicId: Long,
    val isMarkedAsUnread: Boolean
) : Function
