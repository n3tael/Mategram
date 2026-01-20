package com.xxcactussell.domain

data class PageBlockEmbedded(
    val url: String,
    val html: String,
    val posterPhoto: Photo? = null,
    val width: Int,
    val height: Int,
    val caption: PageBlockCaption,
    val isFullWidth: Boolean,
    val allowScrolling: Boolean
) : PageBlock
