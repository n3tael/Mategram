package com.xxcactussell.domain

data class InputMessageAudio(
    val audio: InputFile,
    val albumCoverThumbnail: InputThumbnail?,
    val duration: Int,
    val title: String,
    val performer: String,
    val caption: FormattedText
) : InputMessageContent
