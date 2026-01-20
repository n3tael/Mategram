package com.xxcactussell.domain

data class InputChatPhotoAnimation(
    val animation: InputFile,
    val mainFrameTimestamp: Double
) : InputChatPhoto
