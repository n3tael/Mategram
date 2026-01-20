package com.xxcactussell.domain

data class InputMessageForwarded(
    val fromChatId: Long,
    val messageId: Long,
    val inGameShare: Boolean,
    val replaceVideoStartTimestamp: Boolean,
    val newVideoStartTimestamp: Int,
    val copyOptions: MessageCopyOptions
) : InputMessageContent
