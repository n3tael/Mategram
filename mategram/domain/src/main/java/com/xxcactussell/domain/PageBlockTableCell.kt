package com.xxcactussell.domain

data class PageBlockTableCell(
    val text: RichText? = null,
    val isHeader: Boolean,
    val colspan: Int,
    val rowspan: Int,
    val align: PageBlockHorizontalAlignment,
    val valign: PageBlockVerticalAlignment
) : Object
