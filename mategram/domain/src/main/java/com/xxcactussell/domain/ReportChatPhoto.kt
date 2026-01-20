package com.xxcactussell.domain

data class ReportChatPhoto(
    val chatId: Long,
    val fileId: Int,
    val reason: ReportReason,
    val text: String
) : Function
