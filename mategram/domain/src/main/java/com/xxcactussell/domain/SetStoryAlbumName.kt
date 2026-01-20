package com.xxcactussell.domain

data class SetStoryAlbumName(
    val chatId: Long,
    val storyAlbumId: Int,
    val name: String
) : Function
