package com.xxcactussell.domain

data class StoryAlbum(
    val id: Int,
    val name: String,
    val photoIcon: Photo? = null,
    val videoIcon: Video? = null
) : Object
