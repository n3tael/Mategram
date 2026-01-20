package com.xxcactussell.domain

data class GetChatMessageCount(
    val chatId: Long,
    val topicId: MessageTopic,
    val filter: SearchMessagesFilter,
    val returnLocal: Boolean
) : Function
