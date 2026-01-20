package com.xxcactussell.domain

data class DeleteSavedMessagesTopicMessagesByDate(
    val savedMessagesTopicId: Long,
    val minDate: Int,
    val maxDate: Int
) : Function
