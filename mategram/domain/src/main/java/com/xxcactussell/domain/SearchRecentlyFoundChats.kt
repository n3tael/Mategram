package com.xxcactussell.domain

data class SearchRecentlyFoundChats(
    val query: String,
    val limit: Int
) : Function
