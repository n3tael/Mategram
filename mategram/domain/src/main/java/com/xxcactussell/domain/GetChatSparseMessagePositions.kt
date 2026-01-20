package com.xxcactussell.domain

data class GetChatSparseMessagePositions(
    val chatId: Long,
    val filter: SearchMessagesFilter,
    val fromMessageId: Long,
    val limit: Int,
    val savedMessagesTopicId: Long
) : Function
