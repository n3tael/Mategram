package com.xxcactussell.domain

data class LinkPreviewTypeTheme(
    val documents: List<Document>,
    val settings: ThemeSettings? = null
) : LinkPreviewType
