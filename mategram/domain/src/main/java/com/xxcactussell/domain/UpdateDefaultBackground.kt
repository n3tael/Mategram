package com.xxcactussell.domain

data class UpdateDefaultBackground(
    val forDarkTheme: Boolean,
    val background: Background? = null
) : Update
