package com.xxcactussell.domain

data class SearchStringsByPrefix(
    val strings: List<String>,
    val query: String,
    val limit: Int,
    val returnNoneForEmptyQuery: Boolean
) : Function
