package com.xxcactussell.domain

data class GetStoryPublicForwards(
    val storyPosterChatId: Long,
    val storyId: Int,
    val offset: String,
    val limit: Int
) : Function
