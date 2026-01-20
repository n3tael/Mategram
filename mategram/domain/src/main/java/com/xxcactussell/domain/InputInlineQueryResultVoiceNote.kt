package com.xxcactussell.domain

data class InputInlineQueryResultVoiceNote(
    val id: String,
    val title: String,
    val voiceNoteUrl: String,
    val voiceNoteDuration: Int,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : InputInlineQueryResult
