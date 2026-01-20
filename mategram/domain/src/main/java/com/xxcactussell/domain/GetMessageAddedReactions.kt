package com.xxcactussell.domain

data class GetMessageAddedReactions(
    val chatId: Long,
    val messageId: Long,
    val reactionType: ReactionType,
    val offset: String,
    val limit: Int
) : Function
