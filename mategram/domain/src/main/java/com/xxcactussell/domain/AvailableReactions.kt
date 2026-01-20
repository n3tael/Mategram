package com.xxcactussell.domain

data class AvailableReactions(
    val topReactions: List<AvailableReaction>,
    val recentReactions: List<AvailableReaction>,
    val popularReactions: List<AvailableReaction>,
    val allowCustomEmoji: Boolean,
    val areTags: Boolean,
    val unavailabilityReason: ReactionUnavailabilityReason? = null
) : Object
