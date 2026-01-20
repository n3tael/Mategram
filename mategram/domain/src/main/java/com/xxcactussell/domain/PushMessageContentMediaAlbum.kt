package com.xxcactussell.domain

data class PushMessageContentMediaAlbum(
    val totalCount: Int,
    val hasPhotos: Boolean,
    val hasVideos: Boolean,
    val hasAudios: Boolean,
    val hasDocuments: Boolean
) : PushMessageContent
