package com.xxcactussell.domain

data class MessageSuggestedPostApproved(
    val suggestedPostMessageId: Long,
    val price: SuggestedPostPrice? = null,
    val sendDate: Int
) : MessageContent
