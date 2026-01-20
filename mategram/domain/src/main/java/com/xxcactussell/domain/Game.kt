package com.xxcactussell.domain

data class Game(
    val id: Long,
    val shortName: String,
    val title: String,
    val text: FormattedText,
    val description: String,
    val photo: Photo,
    val animation: Animation? = null
) : Object
