package com.xxcactussell.domain

data class InputInlineQueryResultAnimation(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val thumbnailMimeType: String,
    val videoUrl: String,
    val videoMimeType: String,
    val videoDuration: Int,
    val videoWidth: Int,
    val videoHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
