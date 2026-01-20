package com.xxcactussell.domain

data class InlineQueryResultContact(
    val id: String,
    val contact: Contact,
    val thumbnail: Thumbnail? = null
) : InlineQueryResult
