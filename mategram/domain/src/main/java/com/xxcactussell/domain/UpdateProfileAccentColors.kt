package com.xxcactussell.domain

data class UpdateProfileAccentColors(
    val colors: List<ProfileAccentColor>,
    val availableAccentColorIds: IntArray
) : Update
