package com.xxcactussell.domain

data class GetDirectMessagesChatTopicHistory(
    val chatId: Long,
    val topicId: Long,
    val fromMessageId: Long,
    val offset: Int,
    val limit: Int
) : Function
