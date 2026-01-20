package com.xxcactussell.domain

data class GetUserProfileAudios(
    val userId: Long,
    val offset: Int,
    val limit: Int
) : Function
