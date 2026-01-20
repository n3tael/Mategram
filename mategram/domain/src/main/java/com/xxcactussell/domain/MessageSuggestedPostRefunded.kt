package com.xxcactussell.domain

data class MessageSuggestedPostRefunded(
    val suggestedPostMessageId: Long,
    val reason: SuggestedPostRefundReason
) : MessageContent
