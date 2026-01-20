package com.xxcactussell.domain

data class UpdateMessageReactions(
    val chatId: Long,
    val messageId: Long,
    val date: Int,
    val reactions: List<MessageReaction>
) : Update
