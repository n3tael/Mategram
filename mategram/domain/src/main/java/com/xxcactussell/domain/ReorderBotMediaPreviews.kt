package com.xxcactussell.domain

data class ReorderBotMediaPreviews(
    val botUserId: Long,
    val languageCode: String,
    val fileIds: IntArray
) : Function
