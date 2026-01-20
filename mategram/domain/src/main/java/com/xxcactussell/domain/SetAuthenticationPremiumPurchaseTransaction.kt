package com.xxcactussell.domain

data class SetAuthenticationPremiumPurchaseTransaction(
    val transaction: StoreTransaction,
    val isRestore: Boolean,
    val currency: String,
    val amount: Long
) : Function
