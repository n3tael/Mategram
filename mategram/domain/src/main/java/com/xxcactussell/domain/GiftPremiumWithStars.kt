package com.xxcactussell.domain

data class GiftPremiumWithStars(
    val userId: Long,
    val starCount: Long,
    val monthCount: Int,
    val text: FormattedText
) : Function
