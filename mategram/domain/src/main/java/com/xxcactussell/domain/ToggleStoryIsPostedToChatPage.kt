package com.xxcactussell.domain

data class ToggleStoryIsPostedToChatPage(
    val storyPosterChatId: Long,
    val storyId: Int,
    val isPostedToChatPage: Boolean
) : Function
