package com.xxcactussell.domain

data class CanPostStoryResultMonthlyLimitExceeded(
    val retryAfter: Int
) : CanPostStoryResult
