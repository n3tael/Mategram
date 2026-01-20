package com.xxcactussell.domain

data class PhotoSize(
    val type: String,
    val photo: File,
    val width: Int,
    val height: Int,
    val progressiveSizes: IntArray
) : Object
