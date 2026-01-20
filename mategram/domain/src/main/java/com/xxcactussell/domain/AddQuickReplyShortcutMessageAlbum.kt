package com.xxcactussell.domain

data class AddQuickReplyShortcutMessageAlbum(
    val shortcutName: String,
    val replyToMessageId: Long,
    val inputMessageContents: List<InputMessageContent>
) : Function
