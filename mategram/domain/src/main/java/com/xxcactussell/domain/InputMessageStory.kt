package com.xxcactussell.domain

data class InputMessageStory(
    val storyPosterChatId: Long,
    val storyId: Int
) : InputMessageContent
