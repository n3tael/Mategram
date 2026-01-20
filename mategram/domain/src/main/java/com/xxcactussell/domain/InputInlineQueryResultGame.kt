package com.xxcactussell.domain

data class InputInlineQueryResultGame(
    val id: String,
    val gameShortName: String,
    val replyMarkup: ReplyMarkup
) : InputInlineQueryResult
