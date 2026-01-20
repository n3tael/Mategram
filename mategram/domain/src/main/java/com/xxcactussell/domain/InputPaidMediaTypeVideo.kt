package com.xxcactussell.domain

data class InputPaidMediaTypeVideo(
    val cover: InputFile,
    val startTimestamp: Int,
    val duration: Int,
    val supportsStreaming: Boolean
) : InputPaidMediaType
