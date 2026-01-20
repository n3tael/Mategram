package com.xxcactussell.domain

data class InlineQueryResultPhoto(
    val id: String,
    val photo: Photo,
    val title: String,
    val description: String
) : InlineQueryResult
