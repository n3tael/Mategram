package com.xxcactussell.domain

data class ReceivedGifts(
    val totalCount: Int,
    val gifts: List<ReceivedGift>,
    val areNotificationsEnabled: Boolean,
    val nextOffset: String
) : Object
