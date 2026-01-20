package com.xxcactussell.domain

data class ProductInfo(
    val title: String,
    val description: FormattedText,
    val photo: Photo? = null
) : Object
