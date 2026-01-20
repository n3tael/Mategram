package com.xxcactussell.domain

data class UserSupportInfo(
    val message: FormattedText,
    val author: String,
    val date: Int
) : Object
