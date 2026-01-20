package com.xxcactussell.domain

data class ReplyMarkupShowKeyboard(
    val rows: List<List<KeyboardButton>>,
    val isPersistent: Boolean,
    val resizeKeyboard: Boolean,
    val oneTime: Boolean,
    val isPersonal: Boolean,
    val inputFieldPlaceholder: String
) : ReplyMarkup
