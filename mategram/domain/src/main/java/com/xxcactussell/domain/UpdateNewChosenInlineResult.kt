package com.xxcactussell.domain

data class UpdateNewChosenInlineResult(
    val senderUserId: Long,
    val userLocation: Location? = null,
    val query: String,
    val resultId: String,
    val inlineMessageId: String
) : Update
