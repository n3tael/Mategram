package com.xxcactussell.domain

data class FoundMessages(
    val totalCount: Int,
    val messages: List<Message>,
    val nextOffset: String
) : Object
