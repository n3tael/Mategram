package com.xxcactussell.domain

data class AddFileToDownloads(
    val fileId: Int,
    val chatId: Long,
    val messageId: Long,
    val priority: Int
) : Function
