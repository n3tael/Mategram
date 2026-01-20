package com.xxcactussell.domain

data class GetStory(
    val storyPosterChatId: Long,
    val storyId: Int,
    val onlyLocal: Boolean
) : Function
