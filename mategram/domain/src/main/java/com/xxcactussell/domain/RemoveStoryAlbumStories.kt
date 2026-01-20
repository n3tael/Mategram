package com.xxcactussell.domain

data class RemoveStoryAlbumStories(
    val chatId: Long,
    val storyAlbumId: Int,
    val storyIds: IntArray
) : Function
