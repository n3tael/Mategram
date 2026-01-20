package com.xxcactussell.domain

data class StorePaymentPurposePremiumSubscription(
    val isRestore: Boolean,
    val isUpgrade: Boolean
) : StorePaymentPurpose
