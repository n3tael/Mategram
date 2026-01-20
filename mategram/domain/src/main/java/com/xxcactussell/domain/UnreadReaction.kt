package com.xxcactussell.domain

data class UnreadReaction(
    val type: ReactionType,
    val senderId: MessageSender,
    val isBig: Boolean
) : Object
