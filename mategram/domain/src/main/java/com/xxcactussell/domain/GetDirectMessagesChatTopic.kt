package com.xxcactussell.domain

data class GetDirectMessagesChatTopic(
    val chatId: Long,
    val topicId: Long
) : Function
