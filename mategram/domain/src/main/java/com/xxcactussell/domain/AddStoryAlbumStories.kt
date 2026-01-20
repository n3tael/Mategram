package com.xxcactussell.domain

data class AddStoryAlbumStories(
    val chatId: Long,
    val storyAlbumId: Int,
    val storyIds: IntArray
) : Function
