package com.xxcactussell.domain

data class MessageAudio(
    val audio: Audio,
    val caption: FormattedText
) : MessageContent
