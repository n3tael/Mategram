package com.xxcactussell.domain

data class GetMapThumbnailFile(
    val location: Location,
    val zoom: Int,
    val width: Int,
    val height: Int,
    val scale: Int,
    val chatId: Long
) : Function
