package com.xxcactussell.domain

data class AlternativeVideo(
    val id: Long,
    val width: Int,
    val height: Int,
    val codec: String,
    val hlsFile: File,
    val video: File
) : Object
