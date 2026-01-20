package com.xxcactussell.domain

data class SearchContacts(
    val query: String,
    val limit: Int
) : Function
