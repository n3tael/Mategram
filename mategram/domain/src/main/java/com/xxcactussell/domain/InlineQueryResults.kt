package com.xxcactussell.domain

data class InlineQueryResults(
    val inlineQueryId: Long,
    val button: InlineQueryResultsButton? = null,
    val results: List<InlineQueryResult>,
    val nextOffset: String
) : Object
