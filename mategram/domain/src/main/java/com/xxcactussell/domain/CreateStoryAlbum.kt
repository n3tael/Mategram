package com.xxcactussell.domain

data class CreateStoryAlbum(
    val storyPosterChatId: Long,
    val name: String,
    val storyIds: IntArray
) : Function
