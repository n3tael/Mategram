package com.xxcactussell.domain

data class GetFileDownloadedPrefixSize(
    val fileId: Int,
    val offset: Long
) : Function
