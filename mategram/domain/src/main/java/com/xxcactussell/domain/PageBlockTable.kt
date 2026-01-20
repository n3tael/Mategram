package com.xxcactussell.domain

data class PageBlockTable(
    val caption: RichText,
    val cells: List<List<PageBlockTableCell>>,
    val isBordered: Boolean,
    val isStriped: Boolean
) : PageBlock
