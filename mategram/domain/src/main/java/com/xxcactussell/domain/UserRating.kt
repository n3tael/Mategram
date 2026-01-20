package com.xxcactussell.domain

data class UserRating(
    val level: Int,
    val isMaximumLevelReached: Boolean,
    val rating: Long,
    val currentLevelRating: Long,
    val nextLevelRating: Long
) : Object
