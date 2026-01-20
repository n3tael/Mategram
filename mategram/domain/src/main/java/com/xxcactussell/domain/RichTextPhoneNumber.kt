package com.xxcactussell.domain

data class RichTextPhoneNumber(
    val text: RichText,
    val phoneNumber: String
) : RichText
