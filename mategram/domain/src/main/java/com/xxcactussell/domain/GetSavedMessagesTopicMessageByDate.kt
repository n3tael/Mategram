package com.xxcactussell.domain

data class GetSavedMessagesTopicMessageByDate(
    val savedMessagesTopicId: Long,
    val date: Int
) : Function
