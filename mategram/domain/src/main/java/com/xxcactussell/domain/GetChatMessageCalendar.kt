package com.xxcactussell.domain

data class GetChatMessageCalendar(
    val chatId: Long,
    val topicId: MessageTopic,
    val filter: SearchMessagesFilter,
    val fromMessageId: Long
) : Function
