package com.xxcactussell.domain

data class UpdateTopicMessageCount(
    val chatId: Long,
    val topicId: MessageTopic,
    val messageCount: Int
) : Update
