package com.xxcactussell.domain

data class PremiumSourceLimitExceeded(
    val limitType: PremiumLimitType
) : PremiumSource
