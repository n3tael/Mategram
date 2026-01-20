package com.xxcactussell.domain

data class InputInlineQueryResultDocument(
    val id: String,
    val title: String,
    val description: String,
    val documentUrl: String,
    val mimeType: String,
    val thumbnailUrl: String,
    val thumbnailWidth: Int,
    val thumbnailHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
