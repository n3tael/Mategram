package com.xxcactussell.domain

data class RemoveAllFilesFromDownloads(
    val onlyActive: Boolean,
    val onlyCompleted: Boolean,
    val deleteFromCache: Boolean
) : Function
