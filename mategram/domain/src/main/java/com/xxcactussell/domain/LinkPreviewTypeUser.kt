package com.xxcactussell.domain

data class LinkPreviewTypeUser(
    val photo: ChatPhoto? = null,
    val isBot: Boolean
) : LinkPreviewType
