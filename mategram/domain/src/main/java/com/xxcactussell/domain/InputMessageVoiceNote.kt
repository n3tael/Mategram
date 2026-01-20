package com.xxcactussell.domain

data class InputMessageVoiceNote(
    val voiceNote: InputFile,
    val duration: Int,
    val waveform: ByteArray,
    val caption: FormattedText? = null,
    val selfDestructType: MessageSelfDestructType? = null
) : InputMessageContent
