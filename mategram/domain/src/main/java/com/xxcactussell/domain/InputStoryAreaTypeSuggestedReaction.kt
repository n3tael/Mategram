package com.xxcactussell.domain

data class InputStoryAreaTypeSuggestedReaction(
    val reactionType: ReactionType,
    val isDark: Boolean,
    val isFlipped: Boolean
) : InputStoryAreaType
