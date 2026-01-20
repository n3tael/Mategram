package com.xxcactussell.domain

data class GetChatSimilarChatCount(
    val chatId: Long,
    val returnLocal: Boolean
) : Function
