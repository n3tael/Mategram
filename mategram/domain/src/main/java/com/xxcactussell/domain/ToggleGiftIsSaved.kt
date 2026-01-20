package com.xxcactussell.domain

data class ToggleGiftIsSaved(
    val receivedGiftId: String,
    val isSaved: Boolean
) : Function
