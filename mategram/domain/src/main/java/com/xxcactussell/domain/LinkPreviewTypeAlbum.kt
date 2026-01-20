package com.xxcactussell.domain

data class LinkPreviewTypeAlbum(
    val media: List<LinkPreviewAlbumMedia>,
    val caption: String
) : LinkPreviewType
