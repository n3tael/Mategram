package com.xxcactussell.domain

data class AnswerInlineQuery(
    val inlineQueryId: Long,
    val isPersonal: Boolean,
    val button: InlineQueryResultsButton,
    val results: List<InputInlineQueryResult>,
    val cacheTime: Int,
    val nextOffset: String
) : Function
