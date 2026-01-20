package com.xxcactussell.domain

data class PremiumState(
    val state: FormattedText,
    val paymentOptions: List<PremiumStatePaymentOption>,
    val animations: List<PremiumFeaturePromotionAnimation>,
    val businessAnimations: List<BusinessFeaturePromotionAnimation>
) : Object
