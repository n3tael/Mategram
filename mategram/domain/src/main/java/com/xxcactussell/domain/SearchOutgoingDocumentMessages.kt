package com.xxcactussell.domain

data class SearchOutgoingDocumentMessages(
    val query: String,
    val limit: Int
) : Function
