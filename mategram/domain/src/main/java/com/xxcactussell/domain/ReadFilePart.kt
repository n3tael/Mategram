package com.xxcactussell.domain

data class ReadFilePart(
    val fileId: Int,
    val offset: Long,
    val count: Long
) : Function
