package com.xxcactussell.domain

data class SearchHashtags(
    val prefix: String,
    val limit: Int
) : Function
