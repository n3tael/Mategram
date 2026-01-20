package com.xxcactussell.domain

data class InlineQueryResultVenue(
    val id: String,
    val venue: Venue,
    val thumbnail: Thumbnail? = null
) : InlineQueryResult
