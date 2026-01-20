package com.xxcactussell.domain

data class CreateVideoChat(
    val chatId: Long,
    val title: String,
    val startDate: Int,
    val isRtmpStream: Boolean
) : Function
