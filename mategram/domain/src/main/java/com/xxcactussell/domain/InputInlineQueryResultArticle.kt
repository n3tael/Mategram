package com.xxcactussell.domain

data class InputInlineQueryResultArticle(
    val id: String,
    val url: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val thumbnailWidth: Int,
    val thumbnailHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
