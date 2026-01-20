package com.xxcactussell.domain

data class WebApp(
    val shortName: String,
    val title: String,
    val description: String,
    val photo: Photo,
    val animation: Animation? = null
) : Object
