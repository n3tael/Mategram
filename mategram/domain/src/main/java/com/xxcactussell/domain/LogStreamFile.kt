package com.xxcactussell.domain

data class LogStreamFile(
    val path: String,
    val maxFileSize: Long,
    val redirectStderr: Boolean
) : LogStream
