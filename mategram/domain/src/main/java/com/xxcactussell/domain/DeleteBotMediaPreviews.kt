package com.xxcactussell.domain

data class DeleteBotMediaPreviews(
    val botUserId: Long,
    val languageCode: String,
    val fileIds: IntArray
) : Function
