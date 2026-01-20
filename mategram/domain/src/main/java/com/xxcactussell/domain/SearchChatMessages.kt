package com.xxcactussell.domain

data class SearchChatMessages(
    val chatId: Long,
    val topicId: MessageTopic,
    val query: String,
    val senderId: MessageSender,
    val fromMessageId: Long,
    val offset: Int,
    val limit: Int,
    val filter: SearchMessagesFilter
) : Function
