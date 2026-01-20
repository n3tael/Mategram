package com.xxcactussell.domain

data class ReadBusinessMessage(
    val businessConnectionId: String,
    val chatId: Long,
    val messageId: Long
) : Function
