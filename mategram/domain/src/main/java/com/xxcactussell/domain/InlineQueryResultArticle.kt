package com.xxcactussell.domain

data class InlineQueryResultArticle(
    val id: String,
    val url: String,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail? = null
) : InlineQueryResult
