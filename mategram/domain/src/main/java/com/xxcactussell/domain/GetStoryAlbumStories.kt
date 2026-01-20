package com.xxcactussell.domain

data class GetStoryAlbumStories(
    val chatId: Long,
    val storyAlbumId: Int,
    val offset: Int,
    val limit: Int
) : Function
