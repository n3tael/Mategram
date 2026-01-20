package com.xxcactussell.domain

data class SearchSecretMessages(
    val chatId: Long,
    val query: String,
    val offset: String,
    val limit: Int,
    val filter: SearchMessagesFilter
) : Function
