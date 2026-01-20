package com.xxcactussell.domain

data class InlineKeyboardButtonTypeLoginUrl(
    val url: String,
    val id: Long,
    val forwardText: String
) : InlineKeyboardButtonType
