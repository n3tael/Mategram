package com.xxcactussell.domain

data class InputInlineQueryResultSticker(
    val id: String,
    val thumbnailUrl: String,
    val stickerUrl: String,
    val stickerWidth: Int,
    val stickerHeight: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
