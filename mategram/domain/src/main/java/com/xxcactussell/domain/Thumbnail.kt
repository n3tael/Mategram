package com.xxcactussell.domain

data class Thumbnail(
    val format: ThumbnailFormat,
    val width: Int,
    val height: Int,
    val file: File
) : Object
