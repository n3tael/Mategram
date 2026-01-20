package com.xxcactussell.domain

data class SearchPublicPosts(
    val query: String,
    val offset: String,
    val limit: Int,
    val starCount: Long
) : Function
