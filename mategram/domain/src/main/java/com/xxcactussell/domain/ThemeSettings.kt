package com.xxcactussell.domain

data class ThemeSettings(
    val baseTheme: BuiltInTheme,
    val accentColor: Int,
    val background: Background? = null,
    val outgoingMessageFill: BackgroundFill? = null,
    val animateOutgoingMessageFill: Boolean,
    val outgoingMessageAccentColor: Int
) : Object
