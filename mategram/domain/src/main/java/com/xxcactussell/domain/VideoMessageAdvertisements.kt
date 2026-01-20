package com.xxcactussell.domain

data class VideoMessageAdvertisements(
    val advertisements: List<VideoMessageAdvertisement>,
    val startDelay: Int,
    val betweenDelay: Int
) : Object
