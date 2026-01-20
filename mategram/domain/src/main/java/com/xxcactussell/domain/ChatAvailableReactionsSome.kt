package com.xxcactussell.domain

data class ChatAvailableReactionsSome(
    val reactions: List<ReactionType>,
    val maxReactionCount: Int
) : ChatAvailableReactions
