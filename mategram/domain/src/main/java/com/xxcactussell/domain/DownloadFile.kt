package com.xxcactussell.domain

data class DownloadFile(
    val fileId: Int,
    val priority: Int,
    val offset: Long,
    val limit: Long,
    val synchronous: Boolean
) : Function
