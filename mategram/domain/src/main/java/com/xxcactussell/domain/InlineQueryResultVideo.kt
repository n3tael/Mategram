package com.xxcactussell.domain

data class InlineQueryResultVideo(
    val id: String,
    val video: Video,
    val title: String,
    val description: String
) : InlineQueryResult
