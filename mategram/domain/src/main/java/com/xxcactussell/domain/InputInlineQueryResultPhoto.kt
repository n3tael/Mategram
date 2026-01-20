package com.xxcactussell.domain

data class InputInlineQueryResultPhoto(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val photoUrl: String,
    val photoWidth: Int,
    val photoHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
