package com.xxcactussell.domain

data class InputInlineQueryResultAudio(
    val id: String,
    val title: String,
    val performer: String,
    val audioUrl: String,
    val audioDuration: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
