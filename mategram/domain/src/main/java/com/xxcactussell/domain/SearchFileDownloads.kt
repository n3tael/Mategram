package com.xxcactussell.domain

data class SearchFileDownloads(
    val query: String,
    val onlyActive: Boolean,
    val onlyCompleted: Boolean,
    val offset: String,
    val limit: Int
) : Function
