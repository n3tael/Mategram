package com.xxcactussell.domain

data class ProfileAccentColor(
    val id: Int,
    val lightThemeColors: ProfileAccentColors,
    val darkThemeColors: ProfileAccentColors,
    val minSupergroupChatBoostLevel: Int,
    val minChannelChatBoostLevel: Int
) : Object
