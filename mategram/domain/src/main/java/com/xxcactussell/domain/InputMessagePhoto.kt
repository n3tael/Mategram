package com.xxcactussell.domain

data class InputMessagePhoto(
    val photo: InputFile,
    val thumbnail: InputThumbnail,
    val addedStickerFileIds: IntArray,
    val width: Int,
    val height: Int,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val selfDestructType: MessageSelfDestructType? = null,
    val hasSpoiler: Boolean
) : InputMessageContent
