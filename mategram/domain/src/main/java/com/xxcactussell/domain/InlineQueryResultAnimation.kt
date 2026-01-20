package com.xxcactussell.domain

data class InlineQueryResultAnimation(
    val id: String,
    val animation: Animation,
    val title: String
) : InlineQueryResult
