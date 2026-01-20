package com.xxcactussell.domain

data class StoryAreaTypeSuggestedReaction(
    val reactionType: ReactionType,
    val totalCount: Int,
    val isDark: Boolean,
    val isFlipped: Boolean
) : StoryAreaType
