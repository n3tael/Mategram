package com.xxcactussell.domain

data class PublicForwards(
    val totalCount: Int,
    val forwards: List<PublicForward>,
    val nextOffset: String
) : Object
