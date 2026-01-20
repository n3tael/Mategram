package com.xxcactussell.domain

data class GetChatStoryInteractions(
    val storyPosterChatId: Long,
    val storyId: Int,
    val reactionType: ReactionType,
    val preferForwards: Boolean,
    val offset: String,
    val limit: Int
) : Function
