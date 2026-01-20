package com.xxcactussell.domain

data class StarTransactionTypePaidMessageReceive(
    val senderId: MessageSender,
    val messageCount: Int,
    val commissionPerMille: Int,
    val commissionStarAmount: StarAmount
) : StarTransactionType
