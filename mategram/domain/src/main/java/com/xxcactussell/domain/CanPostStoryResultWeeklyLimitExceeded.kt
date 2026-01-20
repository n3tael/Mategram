package com.xxcactussell.domain

data class CanPostStoryResultWeeklyLimitExceeded(
    val retryAfter: Int
) : CanPostStoryResult
