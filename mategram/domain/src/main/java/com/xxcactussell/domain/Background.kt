package com.xxcactussell.domain

data class Background(
    val id: Long,
    val isDefault: Boolean,
    val isDark: Boolean,
    val name: String,
    val document: Document? = null,
    val type: BackgroundType
) : Object
