package com.xxcactussell.domain

data class PageBlockAudio(
    val audio: Audio? = null,
    val caption: PageBlockCaption
) : PageBlock
