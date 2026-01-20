package com.xxcactussell.domain

data class GetInlineQueryResults(
    val botUserId: Long,
    val chatId: Long,
    val userLocation: Location,
    val query: String,
    val offset: String
) : Function
