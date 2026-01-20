package com.xxcactussell.domain

data class ChatRevenueTransactionTypeFragmentWithdrawal(
    val withdrawalDate: Int,
    val state: RevenueWithdrawalState
) : ChatRevenueTransactionType
