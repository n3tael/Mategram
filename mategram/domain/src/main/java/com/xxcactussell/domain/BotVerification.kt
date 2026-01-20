package com.xxcactussell.domain

data class BotVerification(
    val botUserId: Long,
    val iconCustomEmojiId: Long,
    val customDescription: FormattedText
) : Object
