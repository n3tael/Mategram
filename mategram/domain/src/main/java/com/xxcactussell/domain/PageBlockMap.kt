package com.xxcactussell.domain

data class PageBlockMap(
    val location: Location,
    val zoom: Int,
    val width: Int,
    val height: Int,
    val caption: PageBlockCaption
) : PageBlock
