package com.xxcactussell.domain

data class MessageVideo(
    val video: Video,
    val alternativeVideos: List<AlternativeVideo>,
    val storyboards: List<VideoStoryboard>,
    val cover: Photo? = null,
    val startTimestamp: Int,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val hasSpoiler: Boolean,
    val isSecret: Boolean
) : MessageContent
