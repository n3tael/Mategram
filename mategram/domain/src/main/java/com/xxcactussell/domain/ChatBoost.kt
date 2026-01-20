package com.xxcactussell.domain

data class ChatBoost(
    val id: String,
    val count: Int,
    val source: ChatBoostSource,
    val startDate: Int,
    val expirationDate: Int
) : Object
