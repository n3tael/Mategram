package com.xxcactussell.domain

data class RichTextEmailAddress(
    val text: RichText,
    val emailAddress: String
) : RichText
