package com.xxcactussell.domain

data class SetMessageFactCheck(
    val chatId: Long,
    val messageId: Long,
    val text: FormattedText
) : Function
