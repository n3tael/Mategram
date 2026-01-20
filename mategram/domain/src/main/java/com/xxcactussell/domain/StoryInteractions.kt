package com.xxcactussell.domain

data class StoryInteractions(
    val totalCount: Int,
    val totalForwardCount: Int,
    val totalReactionCount: Int,
    val interactions: List<StoryInteraction>,
    val nextOffset: String
) : Object
