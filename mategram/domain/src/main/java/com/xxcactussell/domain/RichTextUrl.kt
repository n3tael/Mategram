package com.xxcactussell.domain

data class RichTextUrl(
    val text: RichText,
    val url: String,
    val isCached: Boolean
) : RichText
