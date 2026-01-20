package com.xxcactussell.domain

data class InputMessageVideo(
    val video: InputFile,
    val thumbnail: InputThumbnail,
    val cover: InputFile,
    val startTimestamp: Int,
    val addedStickerFileIds: IntArray,
    val duration: Int,
    val width: Int,
    val height: Int,
    val supportsStreaming: Boolean,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val selfDestructType: MessageSelfDestructType?,
    val hasSpoiler: Boolean
) : InputMessageContent
