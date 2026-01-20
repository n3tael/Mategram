package com.xxcactussell.domain

data class MessageSticker(
    val sticker: Sticker,
    val isPremium: Boolean
) : MessageContent
