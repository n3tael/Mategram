package com.xxcactussell.domain

data class MessageCopyOptions(
    val sendCopy: Boolean,
    val replaceCaption: Boolean,
    val newCaption: FormattedText,
    val newShowCaptionAboveMedia: Boolean
) : Object
