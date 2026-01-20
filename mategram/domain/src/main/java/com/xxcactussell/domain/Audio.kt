package com.xxcactussell.domain

data class Audio(
    val duration: Int,
    val title: String,
    val performer: String,
    val fileName: String,
    val mimeType: String,
    val albumCoverMinithumbnail: Minithumbnail? = null,
    val albumCoverThumbnail: Thumbnail? = null,
    val externalAlbumCovers: List<Thumbnail>,
    val audio: File
) : Object
