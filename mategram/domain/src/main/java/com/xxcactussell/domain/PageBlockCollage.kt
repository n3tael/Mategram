package com.xxcactussell.domain

data class PageBlockCollage(
    val pageBlocks: List<PageBlock>,
    val caption: PageBlockCaption
) : PageBlock
