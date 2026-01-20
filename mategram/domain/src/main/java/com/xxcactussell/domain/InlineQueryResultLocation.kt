package com.xxcactussell.domain

data class InlineQueryResultLocation(
    val id: String,
    val location: Location,
    val title: String,
    val thumbnail: Thumbnail? = null
) : InlineQueryResult
