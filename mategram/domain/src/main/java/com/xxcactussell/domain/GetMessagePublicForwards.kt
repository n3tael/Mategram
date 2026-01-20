package com.xxcactussell.domain

data class GetMessagePublicForwards(
    val chatId: Long,
    val messageId: Long,
    val offset: String,
    val limit: Int
) : Function
