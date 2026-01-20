package com.xxcactussell.domain

data class PageBlockRelatedArticle(
    val url: String,
    val title: String,
    val description: String,
    val photo: Photo? = null,
    val author: String,
    val publishDate: Int
) : Object
