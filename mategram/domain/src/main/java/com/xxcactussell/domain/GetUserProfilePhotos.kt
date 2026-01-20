package com.xxcactussell.domain

data class GetUserProfilePhotos(
    val userId: Long,
    val offset: Int,
    val limit: Int
) : Function
