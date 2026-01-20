package com.xxcactussell.domain

data class UpdateStoryStealthMode(
    val activeUntilDate: Int,
    val cooldownUntilDate: Int
) : Update
