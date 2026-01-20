package com.xxcactussell.domain

data class ChatEventAvailableReactionsChanged(
    val oldAvailableReactions: ChatAvailableReactions,
    val newAvailableReactions: ChatAvailableReactions
) : ChatEventAction
