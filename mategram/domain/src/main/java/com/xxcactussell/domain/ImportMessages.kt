package com.xxcactussell.domain

data class ImportMessages(
    val chatId: Long,
    val messageFile: InputFile,
    val attachedFiles: List<InputFile>
) : Function
