package com.xxcactussell.domain

data class ReportChatSponsoredMessage(
    val chatId: Long,
    val messageId: Long,
    val optionId: ByteArray
) : Function
