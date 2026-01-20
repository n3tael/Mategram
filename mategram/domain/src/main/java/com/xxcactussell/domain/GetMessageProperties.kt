package com.xxcactussell.domain

data class GetMessageProperties(
    val chatId: Long,
    val messageId: Long
) : Function
