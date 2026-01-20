package com.xxcactussell.domain

data class ReplyMarkupForceReply(
    val isPersonal: Boolean,
    val inputFieldPlaceholder: String
) : ReplyMarkup
