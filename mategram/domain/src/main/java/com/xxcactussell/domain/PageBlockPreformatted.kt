package com.xxcactussell.domain

data class PageBlockPreformatted(
    val text: RichText,
    val language: String
) : PageBlock
