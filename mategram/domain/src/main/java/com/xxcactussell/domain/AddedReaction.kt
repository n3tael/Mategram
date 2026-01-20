package com.xxcactussell.domain

data class AddedReaction(
    val type: ReactionType,
    val senderId: MessageSender,
    val isOutgoing: Boolean,
    val date: Int
) : Object
