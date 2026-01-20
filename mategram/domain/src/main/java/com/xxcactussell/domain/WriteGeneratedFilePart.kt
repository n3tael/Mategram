package com.xxcactussell.domain

data class WriteGeneratedFilePart(
    val generationId: Long,
    val offset: Long,
    val data: ByteArray
) : Function
