package com.xxcactussell.domain

data class ReplyMarkupInlineKeyboard(
    val rows: List<List<InlineKeyboardButton>>
) : ReplyMarkup
