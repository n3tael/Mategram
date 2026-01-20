package com.xxcactussell.domain

data class RichTextReference(
    val text: RichText,
    val anchorName: String,
    val url: String
) : RichText
