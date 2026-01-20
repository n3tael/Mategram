package com.xxcactussell.domain

data class ReorderStoryAlbumStories(
    val chatId: Long,
    val storyAlbumId: Int,
    val storyIds: IntArray
) : Function
