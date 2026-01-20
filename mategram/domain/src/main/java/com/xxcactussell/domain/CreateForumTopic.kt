package com.xxcactussell.domain

data class CreateForumTopic(
    val chatId: Long,
    val name: String,
    val icon: ForumTopicIcon
) : Function
