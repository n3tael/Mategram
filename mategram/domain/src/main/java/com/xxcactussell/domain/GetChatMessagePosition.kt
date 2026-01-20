package com.xxcactussell.domain

data class GetChatMessagePosition(
    val chatId: Long,
    val topicId: MessageTopic,
    val filter: SearchMessagesFilter,
    val messageId: Long
) : Function
