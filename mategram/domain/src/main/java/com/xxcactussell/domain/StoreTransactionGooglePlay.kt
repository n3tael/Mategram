package com.xxcactussell.domain

data class StoreTransactionGooglePlay(
    val packageName: String,
    val storeProductId: String,
    val purchaseToken: String
) : StoreTransaction
