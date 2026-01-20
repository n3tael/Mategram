package com.xxcactussell.domain

data class FoundPublicPosts(
    val messages: List<Message>,
    val nextOffset: String,
    val searchLimits: PublicPostSearchLimits? = null,
    val areLimitsExceeded: Boolean
) : Object
