package com.xxcactussell.domain

data class GetMessageReadDate(
    val chatId: Long,
    val messageId: Long
) : Function
