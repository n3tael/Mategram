package com.xxcactussell.domain

data class RichTextAnchorLink(
    val text: RichText,
    val anchorName: String,
    val url: String
) : RichText
