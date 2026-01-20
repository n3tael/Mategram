package com.xxcactussell.domain

data class GetChatRevenueTransactions(
    val chatId: Long,
    val offset: String,
    val limit: Int
) : Function
