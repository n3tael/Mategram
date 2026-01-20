package com.xxcactussell.domain

data class MessageSuggestedPostPaid(
    val suggestedPostMessageId: Long,
    val starAmount: StarAmount,
    val tonAmount: Long
) : MessageContent
