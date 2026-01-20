package com.xxcactussell.domain

data class PageBlockAnimation(
    val animation: Animation? = null,
    val caption: PageBlockCaption,
    val needAutoplay: Boolean
) : PageBlock
