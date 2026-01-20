package com.xxcactussell.domain

data class InputMessageAnimation(
    val animation: InputFile,
    val thumbnail: InputThumbnail,
    val addedStickerFileIds: IntArray,
    val duration: Int,
    val width: Int,
    val height: Int,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val hasSpoiler: Boolean
) : InputMessageContent
