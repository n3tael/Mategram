package com.xxcactussell.domain

data class ReportChat(
    val chatId: Long,
    val optionId: ByteArray,
    val messageIds: LongArray,
    val text: String
) : Function
