package com.xxcactussell.domain

data class PageBlockVideo(
    val video: Video? = null,
    val caption: PageBlockCaption,
    val needAutoplay: Boolean,
    val isLooped: Boolean
) : PageBlock
