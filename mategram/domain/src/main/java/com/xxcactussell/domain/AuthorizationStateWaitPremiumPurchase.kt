package com.xxcactussell.domain

data class AuthorizationStateWaitPremiumPurchase(
    val storeProductId: String,
    val supportEmailAddress: String,
    val supportEmailSubject: String
) : AuthorizationState
