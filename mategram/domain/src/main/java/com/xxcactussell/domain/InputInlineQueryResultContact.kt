package com.xxcactussell.domain

data class InputInlineQueryResultContact(
    val id: String,
    val contact: Contact,
    val thumbnailUrl: String,
    val thumbnailWidth: Int,
    val thumbnailHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
