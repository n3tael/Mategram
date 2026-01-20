package com.xxcactussell.domain

data class CanSendGiftResultFail(
    val reason: FormattedText
) : CanSendGiftResult
