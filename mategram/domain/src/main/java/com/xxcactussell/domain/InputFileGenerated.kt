package com.xxcactussell.domain

data class InputFileGenerated(
    val originalPath: String,
    val conversion: String,
    val expectedSize: Long
) : InputFile
