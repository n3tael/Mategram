package com.xxcactussell.utils

fun String.getFileExtension(): String {
    val extension = this.substringAfterLast('.', "")
        .uppercase()
    return extension.ifEmpty { "FILE" }
}