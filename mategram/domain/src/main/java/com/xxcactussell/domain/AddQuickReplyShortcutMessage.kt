package com.xxcactussell.domain

data class AddQuickReplyShortcutMessage(
    val shortcutName: String,
    val replyToMessageId: Long,
    val inputMessageContent: InputMessageContent
) : Function
