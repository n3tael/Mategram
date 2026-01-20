package com.xxcactussell.domain

data class InternalLinkTypeBotStart(
    val botUsername: String,
    val startParameter: String,
    val autostart: Boolean
) : InternalLinkType
