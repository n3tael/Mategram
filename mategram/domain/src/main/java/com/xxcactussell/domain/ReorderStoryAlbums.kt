package com.xxcactussell.domain

data class ReorderStoryAlbums(
    val chatId: Long,
    val storyAlbumIds: IntArray
) : Function
