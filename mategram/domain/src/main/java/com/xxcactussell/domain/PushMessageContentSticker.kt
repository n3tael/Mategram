package com.xxcactussell.domain

data class PushMessageContentSticker(
    val sticker: Sticker? = null,
    val emoji: String,
    val isPinned: Boolean
) : PushMessageContent
