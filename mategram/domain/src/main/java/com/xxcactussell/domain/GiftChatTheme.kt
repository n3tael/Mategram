package com.xxcactussell.domain

data class GiftChatTheme(
    val gift: UpgradedGift,
    val lightSettings: ThemeSettings,
    val darkSettings: ThemeSettings
) : Object
