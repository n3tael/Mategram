package com.xxcactussell.domain

data class UpdateMessageUnreadReactions(
    val chatId: Long,
    val messageId: Long,
    val unreadReactions: List<UnreadReaction>,
    val unreadReactionCount: Int
) : Update
