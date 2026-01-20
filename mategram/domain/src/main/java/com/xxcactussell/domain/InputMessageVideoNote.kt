package com.xxcactussell.domain

data class InputMessageVideoNote(
    val videoNote: InputFile,
    val thumbnail: InputThumbnail? = null,
    val duration: Int,
    val length: Int,
    val selfDestructType: MessageSelfDestructType? = null
) : InputMessageContent
