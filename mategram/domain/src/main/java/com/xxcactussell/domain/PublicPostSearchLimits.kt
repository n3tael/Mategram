package com.xxcactussell.domain

data class PublicPostSearchLimits(
    val dailyFreeQueryCount: Int,
    val remainingFreeQueryCount: Int,
    val nextFreeQueryIn: Int,
    val starCount: Long,
    val isCurrentQueryFree: Boolean
) : Object
