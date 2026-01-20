package com.xxcactussell.domain

data class PageBlockDetails(
    val header: RichText,
    val pageBlocks: List<PageBlock>,
    val isOpen: Boolean
) : PageBlock
