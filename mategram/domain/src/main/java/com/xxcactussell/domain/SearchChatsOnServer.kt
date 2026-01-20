package com.xxcactussell.domain

data class SearchChatsOnServer(
    val query: String,
    val limit: Int
) : Function
