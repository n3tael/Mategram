package com.xxcactussell.domain

data class DeleteDirectMessagesChatTopicHistory(
    val chatId: Long,
    val topicId: Long
) : Function
