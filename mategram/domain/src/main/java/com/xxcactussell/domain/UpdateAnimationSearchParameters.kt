package com.xxcactussell.domain

data class UpdateAnimationSearchParameters(
    val provider: String,
    val emojis: List<String>
) : Update
