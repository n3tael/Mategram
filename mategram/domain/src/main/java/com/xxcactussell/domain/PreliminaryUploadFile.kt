package com.xxcactussell.domain

data class PreliminaryUploadFile(
    val file: InputFile,
    val fileType: FileType,
    val priority: Int
) : Function
