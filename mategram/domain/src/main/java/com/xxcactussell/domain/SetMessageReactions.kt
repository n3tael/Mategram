package com.xxcactussell.domain

data class SetMessageReactions(
    val chatId: Long,
    val messageId: Long,
    val reactionTypes: List<ReactionType>,
    val isBig: Boolean
) : Function
