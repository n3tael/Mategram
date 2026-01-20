package com.xxcactussell.domain

data class UpdateMessageReaction(
    val chatId: Long,
    val messageId: Long,
    val actorId: MessageSender,
    val date: Int,
    val oldReactionTypes: List<ReactionType>,
    val newReactionTypes: List<ReactionType>
) : Update
