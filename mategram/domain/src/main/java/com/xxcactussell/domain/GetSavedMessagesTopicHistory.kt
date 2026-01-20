package com.xxcactussell.domain

data class GetSavedMessagesTopicHistory(
    val savedMessagesTopicId: Long,
    val fromMessageId: Long,
    val offset: Int,
    val limit: Int
) : Function
