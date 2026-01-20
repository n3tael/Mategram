package com.xxcactussell.domain

data class GetMessageThread(
    val chatId: Long,
    val messageId: Long
) : Function
