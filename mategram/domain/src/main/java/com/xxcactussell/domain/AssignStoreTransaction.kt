package com.xxcactussell.domain

data class AssignStoreTransaction(
    val transaction: StoreTransaction,
    val purpose: StorePaymentPurpose
) : Function
