package com.xxcactussell.domain

data class SearchChats(
    val query: String,
    val limit: Int
) : Function
