package com.xxcactussell.domain

data class FileDownload(
    val fileId: Int,
    val message: Message,
    val addDate: Int,
    val completeDate: Int,
    val isPaused: Boolean
) : Object
