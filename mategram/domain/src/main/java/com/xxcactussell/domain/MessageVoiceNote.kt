package com.xxcactussell.domain

data class MessageVoiceNote(
    val voiceNote: VoiceNote,
    val caption: FormattedText,
    val isListened: Boolean
) : MessageContent
