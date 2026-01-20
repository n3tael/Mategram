package com.xxcactussell.domain

data class ChatBoostSourceGiftCode(
    val userId: Long,
    val giftCode: String
) : ChatBoostSource
