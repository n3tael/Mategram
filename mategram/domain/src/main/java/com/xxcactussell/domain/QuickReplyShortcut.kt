package com.xxcactussell.domain

data class QuickReplyShortcut(
    val id: Int,
    val name: String,
    val firstMessage: QuickReplyMessage,
    val messageCount: Int
) : Object
