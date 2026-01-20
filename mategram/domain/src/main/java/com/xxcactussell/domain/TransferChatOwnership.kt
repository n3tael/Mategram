package com.xxcactussell.domain

data class TransferChatOwnership(
    val chatId: Long,
    val userId: Long,
    val password: String
) : Function
