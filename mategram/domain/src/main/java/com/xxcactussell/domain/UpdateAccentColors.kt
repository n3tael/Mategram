package com.xxcactussell.domain

data class UpdateAccentColors(
    val colors: List<AccentColor>,
    val availableAccentColorIds: IntArray
) : Update
