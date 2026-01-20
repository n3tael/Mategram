package com.xxcactussell.domain

data class MessageInteractionInfo(
    val viewCount: Int,
    val forwardCount: Int,
    val replyInfo: MessageReplyInfo? = null,
    val reactions: MessageReactions? = null
) : Object
