package com.xxcactussell.domain

data class InputMessageReplyToStory(
    val storyPosterChatId: Long,
    val storyId: Int
) : InputMessageReplyTo
