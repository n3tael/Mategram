package com.xxcactussell.domain

data class MessageSuggestedPostApprovalFailed(
    val suggestedPostMessageId: Long,
    val price: SuggestedPostPrice
) : MessageContent
