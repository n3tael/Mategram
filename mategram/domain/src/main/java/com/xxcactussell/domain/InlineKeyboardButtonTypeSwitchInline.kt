package com.xxcactussell.domain

data class InlineKeyboardButtonTypeSwitchInline(
    val query: String,
    val targetChat: TargetChat
) : InlineKeyboardButtonType
