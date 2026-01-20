package com.xxcactussell.domain

data class GiftCollection(
    val id: Int,
    val name: String,
    val icon: Sticker? = null,
    val giftCount: Int
) : Object
