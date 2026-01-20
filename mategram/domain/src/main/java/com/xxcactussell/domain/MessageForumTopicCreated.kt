package com.xxcactussell.domain

data class MessageForumTopicCreated(
    val name: String,
    val icon: ForumTopicIcon
) : MessageContent
