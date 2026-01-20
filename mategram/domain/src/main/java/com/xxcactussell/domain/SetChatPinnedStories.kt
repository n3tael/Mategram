package com.xxcactussell.domain

data class SetChatPinnedStories(
    val chatId: Long,
    val storyIds: IntArray
) : Function
