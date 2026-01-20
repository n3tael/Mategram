package com.xxcactussell.domain

data class MessageStory(
    val storyPosterChatId: Long,
    val storyId: Int,
    val viaMention: Boolean
) : MessageContent
