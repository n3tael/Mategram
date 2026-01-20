package com.xxcactussell.domain

data class MessageReactions(
    val reactions: List<MessageReaction>,
    val areTags: Boolean,
    val paidReactors: List<PaidReactor>,
    val canGetAddedReactions: Boolean
) : Object
