package com.xxcactussell.domain

data class SearchPublicMessagesByTag(
    val tag: String,
    val offset: String,
    val limit: Int
) : Function
