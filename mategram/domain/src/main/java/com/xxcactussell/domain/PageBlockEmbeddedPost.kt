package com.xxcactussell.domain

data class PageBlockEmbeddedPost(
    val url: String,
    val author: String,
    val authorPhoto: Photo? = null,
    val date: Int,
    val pageBlocks: List<PageBlock>,
    val caption: PageBlockCaption
) : PageBlock
