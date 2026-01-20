package com.xxcactussell.domain

data class DeleteDirectMessagesChatTopicMessagesByDate(
    val chatId: Long,
    val topicId: Long,
    val minDate: Int,
    val maxDate: Int
) : Function
