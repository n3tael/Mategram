package com.xxcactussell.domain

data class CollectibleItemInfo(
    val purchaseDate: Int,
    val currency: String,
    val amount: Long,
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val url: String
) : Object
