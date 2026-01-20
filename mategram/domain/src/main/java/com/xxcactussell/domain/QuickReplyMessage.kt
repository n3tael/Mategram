package com.xxcactussell.domain

data class QuickReplyMessage(
    val id: Long,
    val sendingState: MessageSendingState? = null,
    val canBeEdited: Boolean,
    val replyToMessageId: Long,
    val viaBotUserId: Long,
    val mediaAlbumId: Long,
    val content: MessageContent,
    val replyMarkup: ReplyMarkup? = null
) : Object
