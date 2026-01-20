package com.xxcactussell.domain

data class MessageAnimatedEmoji(
    val animatedEmoji: AnimatedEmoji,
    val emoji: String
) : MessageContent
