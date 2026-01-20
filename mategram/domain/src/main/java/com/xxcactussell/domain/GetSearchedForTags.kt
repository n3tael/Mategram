package com.xxcactussell.domain

data class GetSearchedForTags(
    val tagPrefix: String,
    val limit: Int
) : Function
