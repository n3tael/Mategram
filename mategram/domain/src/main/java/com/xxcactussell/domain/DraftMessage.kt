package com.xxcactussell.domain

data class DraftMessage(
    val replyTo: InputMessageReplyTo? = null,
    val date: Int,
    val inputMessageText: InputMessageContent,
    val effectId: Long,
    val suggestedPostInfo: InputSuggestedPostInfo? = null
) : Object
