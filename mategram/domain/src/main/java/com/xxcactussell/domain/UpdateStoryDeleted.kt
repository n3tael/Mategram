package com.xxcactussell.domain

data class UpdateStoryDeleted(
    val storyPosterChatId: Long,
    val storyId: Int
) : Update
