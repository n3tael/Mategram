package com.xxcactussell.domain

data class UpdateNewInlineQuery(
    val id: Long,
    val senderUserId: Long,
    val userLocation: Location? = null,
    val chatType: ChatType? = null,
    val query: String,
    val offset: String
) : Update
