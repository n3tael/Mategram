package com.xxcactussell.domain

data class InlineQueryResultDocument(
    val id: String,
    val document: Document,
    val title: String,
    val description: String
) : InlineQueryResult
