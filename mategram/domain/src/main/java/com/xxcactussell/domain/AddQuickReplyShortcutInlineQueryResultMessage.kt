package com.xxcactussell.domain

data class AddQuickReplyShortcutInlineQueryResultMessage(
    val shortcutName: String,
    val replyToMessageId: Long,
    val queryId: Long,
    val resultId: String,
    val hideViaBot: Boolean
) : Function
