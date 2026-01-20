package com.xxcactussell.domain

data class CheckWebAppFileDownload(
    val botUserId: Long,
    val fileName: String,
    val url: String
) : Function
