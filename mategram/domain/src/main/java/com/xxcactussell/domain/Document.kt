package com.xxcactussell.domain

data class Document(
    val fileName: String,
    val mimeType: String,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val document: File
) : Object
