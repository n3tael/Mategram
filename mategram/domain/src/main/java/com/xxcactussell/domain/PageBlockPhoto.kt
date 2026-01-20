package com.xxcactussell.domain

data class PageBlockPhoto(
    val photo: Photo? = null,
    val caption: PageBlockCaption,
    val url: String
) : PageBlock
