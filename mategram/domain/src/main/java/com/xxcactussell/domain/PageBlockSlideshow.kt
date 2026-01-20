package com.xxcactussell.domain

data class PageBlockSlideshow(
    val pageBlocks: List<PageBlock>,
    val caption: PageBlockCaption
) : PageBlock
