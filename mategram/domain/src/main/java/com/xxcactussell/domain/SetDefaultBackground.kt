package com.xxcactussell.domain

data class SetDefaultBackground(
    val background: InputBackground,
    val type: BackgroundType,
    val forDarkTheme: Boolean
) : Function
