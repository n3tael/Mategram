package com.xxcactussell.domain

data class GetStarTransactions(
    val ownerId: MessageSender,
    val subscriptionId: String,
    val direction: TransactionDirection,
    val offset: String,
    val limit: Int
) : Function
