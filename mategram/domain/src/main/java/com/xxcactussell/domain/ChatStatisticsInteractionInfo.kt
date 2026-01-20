package com.xxcactussell.domain

data class ChatStatisticsInteractionInfo(
    val objectType: ChatStatisticsObjectType,
    val viewCount: Int,
    val forwardCount: Int,
    val reactionCount: Int
) : Object
