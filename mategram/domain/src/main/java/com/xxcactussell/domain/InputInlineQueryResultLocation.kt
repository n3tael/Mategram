package com.xxcactussell.domain

data class InputInlineQueryResultLocation(
    val id: String,
    val location: Location,
    val livePeriod: Int,
    val title: String,
    val thumbnailUrl: String,
    val thumbnailWidth: Int,
    val thumbnailHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
