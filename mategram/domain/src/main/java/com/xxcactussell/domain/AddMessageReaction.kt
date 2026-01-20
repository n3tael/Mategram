package com.xxcactussell.domain

data class AddMessageReaction(
    val chatId: Long,
    val messageId: Long,
    val reactionType: ReactionType,
    val isBig: Boolean,
    val updateRecentReactions: Boolean
) : Function
