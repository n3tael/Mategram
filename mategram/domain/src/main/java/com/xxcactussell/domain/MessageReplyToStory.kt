package com.xxcactussell.domain

data class MessageReplyToStory(
    val storyPosterChatId: Long,
    val storyId: Int
) : MessageReplyTo
