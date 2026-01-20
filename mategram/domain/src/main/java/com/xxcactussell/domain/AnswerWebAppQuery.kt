package com.xxcactussell.domain

data class AnswerWebAppQuery(
    val webAppQueryId: String,
    val result: InputInlineQueryResult
) : Function
