package com.xxcactussell.domain

data class InlineQueryResultVoiceNote(
    val id: String,
    val voiceNote: VoiceNote,
    val title: String
) : InlineQueryResult
