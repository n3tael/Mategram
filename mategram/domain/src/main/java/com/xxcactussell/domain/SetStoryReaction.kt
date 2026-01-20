package com.xxcactussell.domain

data class SetStoryReaction(
    val storyPosterChatId: Long,
    val storyId: Int,
    val reactionType: ReactionType,
    val updateRecentReactions: Boolean
) : Function
