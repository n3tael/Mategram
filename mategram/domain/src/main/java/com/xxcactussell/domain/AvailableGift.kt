package com.xxcactussell.domain

data class AvailableGift(
    val gift: Gift,
    val resaleCount: Int,
    val minResaleStarCount: Long,
    val title: String
) : Object
