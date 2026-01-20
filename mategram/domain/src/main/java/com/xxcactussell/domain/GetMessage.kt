package com.xxcactussell.domain

data class GetMessage(
    val chatId: Long,
    val messageId: Long
) : Function
