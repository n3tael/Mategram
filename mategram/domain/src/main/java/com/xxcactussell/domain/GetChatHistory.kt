package com.xxcactussell.domain

data class GetChatHistory(
    val chatId: Long,
    val fromMessageId: Long,
    val offset: Int,
    val limit: Int,
    val onlyLocal: Boolean
) : Function
