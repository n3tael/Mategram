package com.xxcactussell.domain

data class StorePaymentPurposePremiumGiftCodes(
    val boostedChatId: Long,
    val currency: String,
    val amount: Long,
    val userIds: LongArray,
    val text: FormattedText
) : StorePaymentPurpose
