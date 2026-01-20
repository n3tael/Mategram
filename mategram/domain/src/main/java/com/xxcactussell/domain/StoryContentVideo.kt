package com.xxcactussell.domain

data class StoryContentVideo(
    val video: StoryVideo,
    val alternativeVideo: StoryVideo? = null
) : StoryContent
