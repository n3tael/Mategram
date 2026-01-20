package com.xxcactussell.domain

data class GetDirectMessagesChatTopicMessageByDate(
    val chatId: Long,
    val topicId: Long,
    val date: Int
) : Function
