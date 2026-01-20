package com.xxcactussell.domain

data class GetChatBoosts(
    val chatId: Long,
    val onlyGiftCodes: Boolean,
    val offset: String,
    val limit: Int
) : Function
