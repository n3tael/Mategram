package com.xxcactussell.domain

data class GetMessageAuthor(
    val chatId: Long,
    val messageId: Long
) : Function
