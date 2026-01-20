package com.xxcactussell.domain

data class MessageReaction(
    val type: ReactionType,
    val totalCount: Int,
    val isChosen: Boolean,
    val usedSenderId: MessageSender? = null,
    val recentSenderIds: List<MessageSender>
) : Object
