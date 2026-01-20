package com.xxcactussell.domain

data class RemoveMessageReaction(
    val chatId: Long,
    val messageId: Long,
    val reactionType: ReactionType
) : Function
