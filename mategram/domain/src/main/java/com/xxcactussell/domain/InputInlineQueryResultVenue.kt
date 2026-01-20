package com.xxcactussell.domain

data class InputInlineQueryResultVenue(
    val id: String,
    val venue: Venue,
    val thumbnailUrl: String,
    val thumbnailWidth: Int,
    val thumbnailHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
