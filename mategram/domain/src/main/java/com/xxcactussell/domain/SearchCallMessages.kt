package com.xxcactussell.domain

data class SearchCallMessages(
    val offset: String,
    val limit: Int,
    val onlyMissed: Boolean
) : Function
