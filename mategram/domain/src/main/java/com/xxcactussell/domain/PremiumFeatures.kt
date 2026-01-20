package com.xxcactussell.domain

data class PremiumFeatures(
    val features: List<PremiumFeature>,
    val limits: List<PremiumLimit>,
    val paymentLink: InternalLinkType? = null
) : Object
