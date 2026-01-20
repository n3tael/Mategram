package com.xxcactussell.domain

data class VideoMessageAdvertisement(
    val uniqueId: Long,
    val text: String,
    val minDisplayDuration: Int,
    val maxDisplayDuration: Int,
    val canBeReported: Boolean,
    val sponsor: AdvertisementSponsor,
    val title: String,
    val additionalInfo: String
) : Object
