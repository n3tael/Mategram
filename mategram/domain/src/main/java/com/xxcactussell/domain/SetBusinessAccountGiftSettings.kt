package com.xxcactussell.domain

data class SetBusinessAccountGiftSettings(
    val businessConnectionId: String,
    val settings: GiftSettings
) : Function
