package com.xxcactussell.domain

data class PushMessageContentGift(
    val starCount: Long,
    val isPrepaidUpgrade: Boolean
) : PushMessageContent
