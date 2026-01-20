package com.xxcactussell.domain

data class EditQuickReplyMessage(
    val shortcutId: Int,
    val messageId: Long,
    val inputMessageContent: InputMessageContent
) : Function
