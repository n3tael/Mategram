package com.xxcactussell.domain

data class BotVerificationParameters(
    val iconCustomEmojiId: Long,
    val organizationName: String,
    val defaultCustomDescription: FormattedText? = null,
    val canSetCustomDescription: Boolean
) : Object
