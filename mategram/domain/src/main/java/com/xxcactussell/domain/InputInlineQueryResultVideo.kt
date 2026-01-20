package com.xxcactussell.domain

data class InputInlineQueryResultVideo(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val mimeType: String,
    val videoWidth: Int,
    val videoHeight: Int,
    val videoDuration: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
