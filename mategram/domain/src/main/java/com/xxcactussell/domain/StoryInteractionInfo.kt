package com.xxcactussell.domain

data class StoryInteractionInfo(
    val viewCount: Int,
    val forwardCount: Int,
    val reactionCount: Int,
    val recentViewerUserIds: LongArray
) : Object
