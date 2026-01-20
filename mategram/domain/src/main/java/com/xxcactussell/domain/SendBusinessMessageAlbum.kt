package com.xxcactussell.domain

data class SendBusinessMessageAlbum(
    val businessConnectionId: String,
    val chatId: Long,
    val replyTo: InputMessageReplyTo,
    val disableNotification: Boolean,
    val protectContent: Boolean,
    val effectId: Long,
    val inputMessageContents: List<InputMessageContent>
) : Function
