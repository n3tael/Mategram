package com.xxcactussell.domain

data class ReportMessageReactions(
    val chatId: Long,
    val messageId: Long,
    val senderId: MessageSender
) : Function
