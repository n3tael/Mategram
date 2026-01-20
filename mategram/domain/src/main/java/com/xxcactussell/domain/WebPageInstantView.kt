package com.xxcactussell.domain

data class WebPageInstantView(
    val pageBlocks: List<PageBlock>,
    val viewCount: Int,
    val version: Int,
    val isRtl: Boolean,
    val isFull: Boolean,
    val feedbackLink: InternalLinkType
) : Object
