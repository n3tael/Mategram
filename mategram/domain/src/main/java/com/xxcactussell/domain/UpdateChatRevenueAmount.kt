package com.xxcactussell.domain

data class UpdateChatRevenueAmount(
    val chatId: Long,
    val revenueAmount: ChatRevenueAmount
) : Update
