package com.xxcactussell.domain

data class MessageSuggestedPostDeclined(
    val suggestedPostMessageId: Long,
    val comment: String
) : MessageContent
