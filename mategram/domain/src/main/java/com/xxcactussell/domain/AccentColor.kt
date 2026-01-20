package com.xxcactussell.domain

data class AccentColor(
    val id: Int,
    val builtInAccentColorId: Int,
    val lightThemeColors: IntArray,
    val darkThemeColors: IntArray,
    val minChannelChatBoostLevel: Int
) : Object
